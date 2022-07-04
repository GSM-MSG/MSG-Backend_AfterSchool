package com.msg.after_school.domain.after_school.service.impl;


import com.msg.after_school.domain.after_school.dao.AfterSchoolDao;
import com.msg.after_school.domain.after_school.data.dto.response.AfterSchoolListResponseDto;
import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.data.entity.AfterSchoolRegistration;
import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRegistrationRepository;
import com.msg.after_school.domain.after_school.service.AfterSchoolService;
import com.msg.after_school.domain.after_school.service.util.AfterSchoolRegistrationPolicyValidator;
import com.msg.after_school.domain.user.data.entity.User;
import com.msg.after_school.global.user.dao.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AfterSchoolServiceImpl implements AfterSchoolService {
    private final AfterSchoolRegistrationRepository afterSchoolRegistrationRepository;
    private final AfterSchoolRegistrationPolicyValidator afterSchoolRegistrationPolicyValidator;
    private final UserDao userDao;
    private final AfterSchoolDao afterSchoolDao;

    @Override
    @Transactional(readOnly = true)
    public List<AfterSchoolListResponseDto> findAfterSchoolList() {
        List<AfterSchool> afterSchoolList = afterSchoolDao.findAllByIsOpened(true);
        User currentUser = userDao.getCurrentUser();
        List<AfterSchool> appliedAfterSchoolList = afterSchoolRegistrationRepository.findAllByUser(currentUser).stream()
                .map(AfterSchoolRegistration::getAfterSchool)
                .collect(Collectors.toList());

        List<AfterSchoolListResponseDto> afterSchoolListResponseDtoList = afterSchoolList.stream()
                .map(as -> AfterSchoolListResponseDto.builder()
                        .id(as.getId())
                        .title(as.getTitle())
                        .week(as.getDayOfWeek())
                        .grade(as.getGrade())
                        .isOpened(as.getIsOpened())
                        .isApplied(appliedAfterSchoolList.contains(as))
                        .build()
                )
                .collect(Collectors.toList());

        return afterSchoolListResponseDtoList;
    }

    @Override
    @Transactional
    public void applyAfterSchool(Integer AfterSchoolId) {
        //인자로 받은 방과후Id로 방과후정보를 가져온다.
        AfterSchool afterSchoolInfo = afterSchoolDao.getAfterSchoolByAfterSchoolId(AfterSchoolId);
        //현재 요청을 보낸 유저의 정보를 가져온다.
        User userInfo = userDao.getCurrentUser();
        //방과후 가입 정책을 검증한다.
        afterSchoolRegistrationPolicyValidator.validate(afterSchoolInfo, userInfo);
        AfterSchoolRegistration afterSchoolRegistration = AfterSchoolRegistration.builder()
                .afterSchool(afterSchoolInfo)
                .user(userInfo)
                .build();
        afterSchoolRegistrationRepository.save(afterSchoolRegistration);
    }

    @Override
    @Transactional
    public void cancelApplyAfterSchool(Integer AfterSchoolId) {
        //인자로 받은 방과후Id로 방과후정보를 가져온다.
        AfterSchool afterSchoolInfo = afterSchoolDao.getAfterSchoolByAfterSchoolId(AfterSchoolId);
        //현재 요청을 보낸 유저의 정보를 가져온다.
        User userInfo = userDao.getCurrentUser();
        //방과후 가입 취소 정책을 검증한다
        afterSchoolRegistrationPolicyValidator.validateCancelPolicy(afterSchoolInfo, userInfo);
        afterSchoolRegistrationRepository.deleteByUserAndAfterSchool(userInfo, afterSchoolInfo);
    }
}
