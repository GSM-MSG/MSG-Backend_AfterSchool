package com.msg.after_school.domain.after_school.util.impl;
import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.util.AfterSchoolConverter;
import com.msg.after_school.domain.after_school.response.AfterSchoolResponse;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AfterSchoolConverterImpl implements AfterSchoolConverter {
    @Override
    public List<AfterSchoolResponse> toResponse(List<AfterSchool> dtoList) {
        List<AfterSchoolResponse> afterSchoolResponses = new ArrayList<>();
        dtoList.forEach(a -> {
            afterSchoolResponses.add(AfterSchoolResponse.builder()
                        .id(a.getId())
                        .title(a.getTitle())
                        .week(Arrays.asList(a.getDayOfWeek().stream().map(d -> d.getDayOfWeek()).toArray(String[]::new)))
                        .grade(Arrays.asList(a.getGrade().stream().map(g -> g.getGrade()).toArray(Long[]::new)))
                        .isOpened(a.getIsOpened())
                        .build());
        });
        return afterSchoolResponses;
    }
}
