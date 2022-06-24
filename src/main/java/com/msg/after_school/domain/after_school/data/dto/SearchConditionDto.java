package com.msg.after_school.domain.after_school.data.dto;

import com.msg.after_school.domain.after_school.data.type.SeasonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class SearchConditionDto {
    private String week;
    private SeasonType season;
    private Long grade;
}
