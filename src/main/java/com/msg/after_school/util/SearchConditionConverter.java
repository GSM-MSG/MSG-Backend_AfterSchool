package com.msg.after_school.util;

import com.msg.after_school.dto.SearchConditionDto;
import com.msg.after_school.type.SeasonType;
import com.msg.after_school.type.WeekType;

public interface SearchConditionConverter {
    SearchConditionDto toDto(SeasonType season, WeekType week, Integer grade);
}
