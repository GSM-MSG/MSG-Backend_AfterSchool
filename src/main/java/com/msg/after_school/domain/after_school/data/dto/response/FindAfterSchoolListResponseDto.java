package com.msg.after_school.domain.after_school.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAfterSchoolListResponseDto {
    private List<AfterSchoolResponseDto> lists;
    private Integer currentGrade;
    private List<Integer> appliedGrades;
}
