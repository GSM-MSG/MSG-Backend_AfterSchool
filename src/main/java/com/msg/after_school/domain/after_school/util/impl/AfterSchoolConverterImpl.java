package com.msg.after_school.domain.after_school.util.impl;

import com.msg.after_school.domain.after_school.data.dto.AfterSchoolDto;
import com.msg.after_school.domain.after_school.data.response.AfterSchoolResponse;
import com.msg.after_school.domain.after_school.util.AfterSchoolConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AfterSchoolConverterImpl implements AfterSchoolConverter {
    @Override
    public List<AfterSchoolResponse> toResponse(List<AfterSchoolDto> dtoList) {
        List<AfterSchoolResponse> afterSchoolResponses = new ArrayList<>();
        dtoList.forEach(a -> {
            afterSchoolResponses.add(AfterSchoolResponse.builder().build());
        });
        return afterSchoolResponses;
    }
}
