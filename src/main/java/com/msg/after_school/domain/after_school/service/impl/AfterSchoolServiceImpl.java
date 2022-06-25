package com.msg.after_school.domain.after_school.service.impl;

import com.msg.after_school.service.AfterSchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRepository;
import com.msg.after_school.domain.after_school.facade.AfterSchoolFacade;
import com.msg.after_school.domain.after_school.repository.DayOfWeekRepository;
import com.msg.after_school.domain.user.entity.User;
import com.msg.after_school.global.user.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AfterSchoolServiceImpl implements AfterSchoolService {

    private final AfterSchoolRepository afterSchoolRepository;
    private final UserFacade userFacade;
    private final AfterSchoolFacade afterSchoolFacade;
    private final DayOfWeekRepository dayOfWeekRepository;

    @Override
    @Transactional
    public List<AfterSchool> findAfterSchoolListBySearchCondition() { // 1 학년 월 , 수
        List<AfterSchool> afterSchoolList = afterSchoolRepository.findAll();
        List<AfterSchool> filteredList = Arrays.asList(applyFilter(afterSchoolList));
        return filteredList;
    }

    @Override
    public void applyAfterSchool(Long AfterSchoolId) {
        AfterSchool afterSchoolInfo=afterSchoolFacade.getAfterSchoolByAfterSchoolId(AfterSchoolId);
        User userInfo=userFacade.getCurrentUser();

        System.out.println(afterSchoolInfo);
    }

    private AfterSchool[] applyFilter(List<AfterSchool> afterSchoolList) {
        return afterSchoolList.stream().filter(afterSchool -> {
                    return afterSchool.getIsOpened() == true;
                }
        ).toArray(AfterSchool[] :: new);
    }
}
