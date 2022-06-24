package com.msg.after_school.domain.after_school.service;

import com.msg.after_school.domain.after_school.data.dto.AfterSchoolDto;
import com.msg.after_school.domain.after_school.data.dto.ApplyAfterSchoolDto;
import com.msg.after_school.domain.after_school.data.dto.SearchConditionDto;

import java.util.List;

public interface AfterSchoolService {
    List<AfterSchoolDto> findAfterSchoolListBySearchCondition(SearchConditionDto dto);

    void applyAfterSchool(Long AfterSchoolId);
}
