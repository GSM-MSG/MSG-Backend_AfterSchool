package com.msg.after_school.util;

import com.msg.after_school.domain.DayOfWeek;
import com.msg.after_school.dto.SearchConditionDto;
import com.msg.after_school.type.SeasonType;

public interface SearchConditionConverter {
    SearchConditionDto toDto(SeasonType season, String week, Long grade);
}
