package com.msg.after_school.domain.after_school.util.impl;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.data.entity.ClassRegistration;
import com.msg.after_school.domain.after_school.exception.AfterSchoolConflictException;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRegistrationRepository;
import com.msg.after_school.domain.after_school.util.AfterSchoolRegistrationPolicyValidator;
import com.msg.after_school.domain.user.entity.User;
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

    //요청자가 이미 해당 방과후에 신청하였는지 검사한다.
    private void checkAlreadyRegistered(AfterSchool afterSchoolInfo, User userInfo) {
        boolean checkAlreadyRegistered = afterSchoolRegistrationRepository.existsByUserAndAfterSchool(userInfo,afterSchoolInfo);
        if(checkAlreadyRegistered) throw new AfterSchoolConflictException();
    }

    //요청자가 같은 요일에 진행하는 다른 방과후에 신청하였는지 검사한다.
    private void checkRegisteredAnotherAfterSchool(AfterSchool afterSchoolInfo, User userInfo) {
        List<ClassRegistration> allAfterSchool = afterSchoolRegistrationRepository.findAllJoinFetch();
        boolean checkRegisteredAnotherAfterSchool = allAfterSchool.stream().noneMatch (afterSchoolRegistration ->
                checkDayOfWeek(afterSchoolRegistration, afterSchoolInfo) && checkUser(afterSchoolRegistration, userInfo));

        if(!checkRegisteredAnotherAfterSchool) throw new AfterSchoolConflictException ();
    }

    private Boolean checkUser(ClassRegistration afterSchoolRegistration, User userInfo) {
        return Objects.equals(afterSchoolRegistration.getUser().getEmail(), userInfo.getEmail()); }
    private Boolean checkDayOfWeek(ClassRegistration afterSchoolRegistration, AfterSchool afterSchoolInfo) {
        return afterSchoolRegistration.getAfterSchool().getDayOfWeek().stream().noneMatch(dow -> afterSchoolInfo.getDayOfWeek().contains(dow));}
}
