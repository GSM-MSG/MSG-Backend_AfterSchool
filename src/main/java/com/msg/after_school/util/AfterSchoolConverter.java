package com.msg.after_school.util;

import com.msg.after_school.dto.AfterSchoolDto;
import com.msg.after_school.response.AfterSchoolResponse;

import java.util.List;

public interface AfterSchoolConverter {
    List<AfterSchoolResponse> toResponse(List<AfterSchoolDto> dtoList);
}
