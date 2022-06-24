package com.msg.after_school.domain.after_school.util.impl;

<<<<<<< HEAD:src/main/java/com/msg/after_school/util/impl/SearchConditionConverterImpl.java
import com.msg.after_school.dto.SearchConditionDto;
import com.msg.after_school.type.SeasonType;
import com.msg.after_school.util.SearchConditionConverter;
=======
import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.data.dto.SearchConditionDto;
import com.msg.after_school.domain.after_school.data.type.SeasonType;
import com.msg.after_school.domain.after_school.util.SearchConditionConverter;
>>>>>>> origin/feat/apply:src/main/java/com/msg/after_school/domain/after_school/util/impl/SearchConditionConverterImpl.java
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
