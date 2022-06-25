package com.msg.after_school.domain.after_school.service.impl;

import com.msg.after_school.domain.after_school.service.AfterSchoolService;
import lombok.RequiredArgsConstructor;
import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRepository;
import com.msg.after_school.domain.after_school.facade.AfterSchoolFacade;
import com.msg.after_school.domain.user.entity.User;
import com.msg.after_school.global.user.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AfterSchoolServiceImpl implements AfterSchoolService {

    private final AfterSchoolRepository afterSchoolRepository;
    private final UserFacade userFacade;
    private final AfterSchoolFacade afterSchoolFacade;

    @Override
    @Transactional (readOnly = true)
    public List<AfterSchool> findAfterSchoolList() { // 1 학년 월 , 수
        List<AfterSchool> afterSchoolList = afterSchoolRepository.findAllByIsOpened(true);
        return afterSchoolList;
    }

    @Override
    public void applyAfterSchool(Long AfterSchoolId) {
        AfterSchool afterSchoolInfo=afterSchoolFacade.getAfterSchoolByAfterSchoolId(AfterSchoolId);
        User userInfo=userFacade.getCurrentUser();
    }
}
