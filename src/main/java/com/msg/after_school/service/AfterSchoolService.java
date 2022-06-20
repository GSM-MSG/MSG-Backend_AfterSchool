package com.msg.after_school.service;

import com.msg.after_school.dto.AfterSchoolDto;
import com.msg.after_school.dto.SearchConditionDto;

import java.util.List;

public interface AfterSchoolService {
    List<AfterSchoolDto> findAfterSchoolListBySearchCondition(SearchConditionDto dto);
}
