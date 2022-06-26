package com.msg.after_school.domain.after_school.service.impl;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.data.entity.ClassRegistration;
import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.data.dto.AfterSchoolDto;
import com.msg.after_school.domain.after_school.data.dto.SearchConditionDto;
import com.msg.after_school.domain.after_school.exception.AfterSchoolConflictException;
import com.msg.after_school.domain.after_school.facade.AfterSchoolFacade;
import com.msg.after_school.domain.after_school.repository.ClassRegistrationRepository;
import com.msg.after_school.domain.after_school.service.AfterSchoolService;
import com.msg.after_school.domain.after_school.data.type.SeasonType;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRepository;
import com.msg.after_school.domain.after_school.repository.DayOfWeekRepository;
import com.msg.after_school.domain.user.entity.User;
import com.msg.after_school.global.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AfterSchoolServiceImpl implements AfterSchoolService {
    private final AfterSchoolRepository afterSchoolRepository;
    private final UserFacade userFacade;
    private final AfterSchoolFacade afterSchoolFacade;
    private final ClassRegistrationRepository classRegistrationRepository;
    private final DayOfWeekRepository dayOfWeekRepository;

    @Override
    @Transactional
    public List<AfterSchoolDto> findAfterSchoolListBySearchCondition(SearchConditionDto searchConditionDto) {
        Integer grade = searchConditionDto.getGrade();
        SeasonType season = searchConditionDto.getSeason();
        DayOfWeek dayOfWeek = searchConditionDto.getWeek();
        List<AfterSchool> data = afterSchoolRepository.findAllByGradeAndDayOfWeekAndSeason(grade,dayOfWeek,season);
        return null;
    }

    @Override
    public void applyAfterSchool(Long AfterSchoolId) {
        AfterSchool afterSchoolInfo=afterSchoolFacade.getAfterSchoolByAfterSchoolId(AfterSchoolId);
        User userInfo=userFacade.getCurrentUser();

        Boolean classRegistrationList = classRegistrationRepository.existsByUserAndAfterSchool(userInfo,afterSchoolInfo);


        if(classRegistrationList) throw new AfterSchoolConflictException ();

        ClassRegistration classRegistration=ClassRegistration.builder()
                .afterSchool(afterSchoolInfo)
                .user(userInfo)
                .build();

        classRegistrationRepository.save(classRegistration);
    }


}
