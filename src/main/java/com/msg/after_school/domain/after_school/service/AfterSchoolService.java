package com.msg.after_school.domain.after_school.service;


import com.msg.after_school.domain.after_school.data.dto.response.AfterSchoolResponseDto;
import com.msg.after_school.domain.after_school.data.dto.response.FindAfterSchoolListResponseDto;

import java.util.List;

public interface AfterSchoolService {
    FindAfterSchoolListResponseDto findAfterSchoolList();

    void applyAfterSchool(Integer AfterSchoolId);

    void cancelApplyAfterSchool(Integer AfterSchoolId);
}
