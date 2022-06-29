package com.msg.after_school.domain.after_school.data.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AfterSchoolResponse {
    private final Integer id;
    private final String title;
    private final List<String> week;
    private final List<Integer> grade;
    private final Boolean isOpened;
}
