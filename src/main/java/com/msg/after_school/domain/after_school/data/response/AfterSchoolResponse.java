package com.msg.after_school.domain.after_school.data.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder //TODO builder제거 후 모든 field에 final 속성 추가 및 RequiredArgsConstructor 추가
public class AfterSchoolResponse {
    private Integer id;
    private String title;
    private List<String> week;
    private List<Integer> grade;
    private Boolean isOpened;
}
