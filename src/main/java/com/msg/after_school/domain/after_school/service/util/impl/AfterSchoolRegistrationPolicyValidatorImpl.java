package com.msg.after_school.domain.after_school.service.util.impl;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.data.entity.AfterSchoolRegistration;
import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.exception.AlreadyExistException;
import com.msg.after_school.domain.after_school.exception.AlreadyJoinedAnotherAfterSchoolException;
import com.msg.after_school.domain.after_school.exception.RegistrationNotFound;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRegistrationRepository;
import com.msg.after_school.domain.after_school.service.util.AfterSchoolRegistrationPolicyValidator;
import com.msg.after_school.domain.user.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AfterSchoolRegistrationPolicyValidatorImpl implements AfterSchoolRegistrationPolicyValidator {
    private final AfterSchoolRegistrationRepository afterSchoolRegistrationRepository;
    @Override
    public void validate(AfterSchool afterSchoolInfo, User userInfo) {
        checkAlreadyRegistered (afterSchoolInfo, userInfo);
        checkRegisteredAnotherAfterSchool(afterSchoolInfo, userInfo);
    }

    @Override
    public void validateCancelPolicy(AfterSchool afterSchoolInfo, User userInfo) {
        checkCancelRegistered (afterSchoolInfo,userInfo);
    }

    //요청자가 취소할 방과후에 가입되어있는지 확인한다
    private void checkCancelRegistered(AfterSchool afterSchoolInfo, User userInfo) {
        boolean checkCancelRegistered = afterSchoolRegistrationRepository.existsByUserAndAfterSchool(userInfo,afterSchoolInfo);
        if(!checkCancelRegistered) {
            throw new RegistrationNotFound();
        }
    }

    //요청자가 이미 해당 방과후에 신청하였는지 검사한다.
    private void checkAlreadyRegistered(AfterSchool afterSchoolInfo, User userInfo) {
        boolean checkAlreadyRegistered = afterSchoolRegistrationRepository.existsByUserAndAfterSchool(userInfo,afterSchoolInfo);
        if(checkAlreadyRegistered) {
            throw new AlreadyExistException();
        }
    }

    //요청자가 같은 요일에 진행하는 다른 방과후에 신청하였는지 검사한다.
    private void checkRegisteredAnotherAfterSchool(AfterSchool afterSchoolInfo, User userInfo) {
        List<AfterSchoolRegistration> allAfterSchool = afterSchoolRegistrationRepository.findAllJoinFetch();
        boolean checkRegisteredAnotherAfterSchool = allAfterSchool.stream().noneMatch (afterSchoolRegistration ->
                checkDayOfWeek(afterSchoolRegistration, afterSchoolInfo) && checkUser(afterSchoolRegistration, userInfo));

        if(!checkRegisteredAnotherAfterSchool) throw new AlreadyJoinedAnotherAfterSchoolException();
    }

    private Boolean checkUser(AfterSchoolRegistration afterSchoolRegistration, User userInfo) {
        return Objects.equals(afterSchoolRegistration.getUser().getEmail(), userInfo.getEmail());
    }
    private Boolean checkDayOfWeek(AfterSchoolRegistration afterSchoolRegistration, AfterSchool afterSchoolInfo) {
        return afterSchoolRegistration.getAfterSchool().getDayOfWeek().stream().map(DayOfWeek::getDayOfWeek).noneMatch(
                dow -> afterSchoolInfo.getDayOfWeek().stream().map(DayOfWeek::getDayOfWeek).anyMatch(it -> checkDayOfWeekString(dow, it)));
    }
    private Boolean checkDayOfWeekString(String lhs, String rhs) {
        return Objects.equals(lhs, rhs);
    }
}
