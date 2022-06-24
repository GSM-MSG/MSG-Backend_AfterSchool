package com.msg.after_school.service;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;

import java.util.List;

public interface AfterSchoolService {
    List<AfterSchool> findAfterSchoolListBySearchCondition();
}
