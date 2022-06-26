package com.msg.after_school.domain.after_school.service.impl;

import com.msg.after_school.domain.after_school.data.dto.AfterSchoolDto;
import com.msg.after_school.domain.after_school.data.dto.SearchConditionDto;
import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.data.entity.ClassRegistration;
import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.data.type.SeasonType;
import com.msg.after_school.domain.after_school.facade.AfterSchoolFacade;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRegistrationRepository;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRepository;
import com.msg.after_school.domain.after_school.repository.DayOfWeekRepository;
import com.msg.after_school.domain.after_school.service.AfterSchoolService;
import com.msg.after_school.domain.after_school.util.AfterSchoolRegistrationPolicyValidator;
import com.msg.after_school.domain.user.entity.User;
import com.msg.after_school.global.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AfterSchoolServiceImpl implements AfterSchoolService {
    private final AfterSchoolRepository afterSchoolRepository;
    private final AfterSchoolRegistrationRepository afterSchoolRegistrationRepository;
    private final DayOfWeekRepository dayOfWeekRepository;

    private final AfterSchoolFacade afterSchoolFacade;
    private final UserFacade userFacade;

    private final AfterSchoolRegistrationPolicyValidator afterSchoolRegistrationPolicyValidator;

    @Override @Transactional
    public List<AfterSchoolDto> findAfterSchoolListBySearchCondition(SearchConditionDto searchConditionDto) {
        Integer grade = searchConditionDto.getGrade();
        SeasonType season = searchConditionDto.getSeason();
        DayOfWeek dayOfWeek = searchConditionDto.getWeek();
        List<AfterSchool> data = afterSchoolRepository.findAllByGradeAndDayOfWeekAndSeason(grade,dayOfWeek,season);
        return null;
    }

    @Override
    public void applyAfterSchool(Long AfterSchoolId) {
        //인자로 받은 방과후Id로 방과후정보를 가져온다.
        AfterSchool afterSchoolInfo = afterSchoolFacade.getAfterSchoolByAfterSchoolId(AfterSchoolId);
        //현재 요청을 보낸 유저의 정보를 가져온다.
        User userInfo = userFacade.getCurrentUser();
        //방과후 가입 정책을 검증한다.
        afterSchoolRegistrationPolicyValidator.validate(afterSchoolInfo, userInfo);
        ClassRegistration classRegistration = ClassRegistration.builder()
                .afterSchool(afterSchoolInfo)
                .user(userInfo)
                .build();
        afterSchoolRegistrationRepository.save(classRegistration);
    }


}
