package com.msg.after_school.domain.after_school.util.impl;
import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.data.response.AfterSchoolResponse;
import com.msg.after_school.domain.after_school.util.AfterSchoolConverter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AfterSchoolConverterImpl implements AfterSchoolConverter {
    @Override
    public List<AfterSchoolResponse> toResponse(List<AfterSchool> dtoList) {
        List<AfterSchoolResponse> afterSchoolResponses = new ArrayList<>();
        dtoList.forEach(dto -> {
            afterSchoolResponses.add(AfterSchoolResponse.builder()
                        .id(dto.getId())
                        .title(dto.getTitle())
                        .week(Arrays.asList(dto.getDayOfWeek().stream().map(d -> d.getDayOfWeek()).toArray(String[]::new)))
                        .grade(Arrays.asList(dto.getGrade().stream().map(g -> g.getGrade()).toArray(Integer[]::new)))
                        .isOpened(dto.getIsOpened())
                        .build());
        });
        return afterSchoolResponses;
    }
}
