package com.msg.after_school.domain.after_school.data.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AfterSchoolResponse {
    private Integer id;
    private String title;
    private List<String> week;
    private List<Integer> grade;
    private Boolean isOpened;
}
