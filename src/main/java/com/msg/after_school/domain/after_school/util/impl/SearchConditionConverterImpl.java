package com.msg.after_school.domain.after_school.util.impl;

import com.msg.after_school.util.SearchConditionConverter;
import com.msg.after_school.domain.after_school.data.dto.SearchConditionDto;
import com.msg.after_school.domain.after_school.data.type.SeasonType;
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
