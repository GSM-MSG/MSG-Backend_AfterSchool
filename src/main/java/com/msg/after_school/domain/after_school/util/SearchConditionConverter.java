package com.msg.after_school.domain.after_school.util;

import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.data.dto.SearchConditionDto;
import com.msg.after_school.domain.after_school.data.type.SeasonType;

public interface SearchConditionConverter {
    SearchConditionDto toDto(SeasonType season, DayOfWeek week, Integer grade);
}
