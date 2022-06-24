package com.msg.after_school.domain.after_school.service.impl;

import com.msg.after_school.service.AfterSchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
<<<<<<< HEAD
import com.msg.after_school.domain.after_school.repository.AfterSchoolRepository;
=======
import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.data.dto.AfterSchoolDto;
import com.msg.after_school.domain.after_school.data.dto.SearchConditionDto;
import com.msg.after_school.domain.after_school.facade.AfterSchoolFacade;
import com.msg.after_school.domain.after_school.service.AfterSchoolService;
import com.msg.after_school.domain.after_school.data.type.SeasonType;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRepository;
import com.msg.after_school.domain.after_school.repository.DayOfWeekRepository;
import com.msg.after_school.domain.user.entity.User;
import com.msg.after_school.global.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
>>>>>>> 10cbe90d1caa42176d9f191cacfeb66f12a5b9d6
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AfterSchoolServiceImpl implements AfterSchoolService {
<<<<<<< HEAD

    private final AfterSchoolRepository afterSchoolRepository;
=======
    private final AfterSchoolRepository afterSchoolRepository;
    private final UserFacade userFacade;
    private final AfterSchoolFacade afterSchoolFacade;
    private final DayOfWeekRepository dayOfWeekRepository;
>>>>>>> 10cbe90d1caa42176d9f191cacfeb66f12a5b9d6

    @Override
    @Transactional
    public List<AfterSchool> findAfterSchoolListBySearchCondition() { // 1 학년 월 , 수
        List<AfterSchool> afterSchoolList = afterSchoolRepository.findAll();
        return afterSchoolList;
    }

    @Override
    public void applyAfterSchool(Long AfterSchoolId) {
        AfterSchool afterSchoolInfo=afterSchoolFacade.getAfterSchoolByAfterSchoolId(AfterSchoolId);
        User userInfo=userFacade.getCurrentUser();

        System.out.println(afterSchoolInfo);
    }


}
