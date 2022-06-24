package com.msg.after_school.service;

import com.msg.after_school.domain.AfterSchool;
import com.msg.after_school.dto.SearchConditionDto;

import java.util.List;

public interface AfterSchoolService {
    List<AfterSchool> findAfterSchoolListBySearchCondition();
}
