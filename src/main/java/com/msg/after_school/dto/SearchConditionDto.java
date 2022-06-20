package com.msg.after_school.dto;

import com.msg.after_school.domain.DayOfWeek;
import com.msg.after_school.type.SeasonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class SearchConditionDto {
    private DayOfWeek week;
    private SeasonType season;
    private Integer grade;
}
