package com.msg.after_school.domain.after_school.data.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msg.after_school.domain.after_school.data.type.SeasonType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class AfterSchoolResponseDto {
    private Integer id;
    private String title;
    private List<Integer> grade;
    private List<String> dayOfWeek;
    private SeasonType season;
    private Boolean isOpened;
    private String teacher;
    private Boolean isApplied;
}
