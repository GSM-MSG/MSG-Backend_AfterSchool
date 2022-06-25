package com.msg.after_school.domain.after_school.util;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.data.response.AfterSchoolResponse;

import java.util.List;

public interface AfterSchoolConverter{
    List<AfterSchoolResponse> toResponse(List<AfterSchool> dtoList);
}