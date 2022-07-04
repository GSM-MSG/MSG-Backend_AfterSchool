package com.msg.after_school.domain.after_school.data.dto.response;

import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.data.entity.Grade;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AfterSchoolListResponseDto {
    private Integer id;
    private String title;
    private List<Grade> grade;
    private List<DayOfWeek> week;
    private Boolean isOpened;
    private Boolean isApplied;
}
