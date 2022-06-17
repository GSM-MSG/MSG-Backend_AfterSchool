package com.msg.after_school.util;

import com.msg.after_school.domain.afterSchool.DayOfWeek;
import com.msg.after_school.dto.SearchConditionDto;
import com.msg.after_school.type.SeasonType;

public interface SearchConditionConverter {
    SearchConditionDto toDto(SeasonType season, DayOfWeek week, Integer grade);
}
