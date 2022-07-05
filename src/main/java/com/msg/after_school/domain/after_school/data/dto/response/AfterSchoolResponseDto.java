package com.msg.after_school.domain.after_school.data.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AfterSchoolResponseDto {
    private Integer id;
    private String title;
    private List<Integer> grade;
    private List<String> week;
    private Boolean isOpened;
    private Boolean isApplied;
}
