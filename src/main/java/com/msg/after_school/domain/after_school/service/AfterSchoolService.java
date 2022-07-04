package com.msg.after_school.domain.after_school.service;


import com.msg.after_school.domain.after_school.data.dto.response.AfterSchoolListResponseDto;
import com.msg.after_school.domain.after_school.data.entity.AfterSchool;

import java.util.List;

public interface AfterSchoolService {
    List<AfterSchoolListResponseDto> findAfterSchoolList();

    void applyAfterSchool(Integer AfterSchoolId);

    void cancelApplyAfterSchool(Integer AfterSchoolId);
}
