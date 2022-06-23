package com.msg.after_school.util.impl;

import com.msg.after_school.dto.SearchConditionDto;
import com.msg.after_school.type.SeasonType;
import com.msg.after_school.util.SearchConditionConverter;
import org.springframework.stereotype.Component;

@Component
public class SearchConditionConverterImpl implements SearchConditionConverter {
    @Override
    public SearchConditionDto toDto(SeasonType season, String week, Long grade) {
        return SearchConditionDto.builder()
                .season(season)
                .week(week)
                .grade(grade)
                .build();
    }
}
