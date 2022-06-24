package com.msg.after_school.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AfterSchoolResponse {
    private Long id;
    private String title;
    private List<String> week;
    private List<Long> grade;
    private Boolean isOpened;
}
