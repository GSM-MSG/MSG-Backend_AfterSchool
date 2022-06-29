package com.msg.after_school.domain.after_school.util.impl;
import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.data.entity.Grade;
import com.msg.after_school.domain.after_school.data.response.AfterSchoolResponse;
import com.msg.after_school.domain.after_school.util.AfterSchoolConverter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class AfterSchoolConverterImpl implements AfterSchoolConverter {
    @Override
    public List<AfterSchoolResponse> toResponse(List<AfterSchool> dtoList) {
        List<AfterSchoolResponse> afterSchoolResponses = new ArrayList<>();
        dtoList.forEach(dto -> {
            Integer id=dto.getId();
            String title=dto.getTitle();
            String[] dayOfWeek = dto.getDayOfWeek().stream().map(DayOfWeek::getDayOfWeek).toArray(String[]::new);
            Integer[] grade=dto.getGrade().stream().map(Grade::getGrade).toArray(Integer[]::new);
            Boolean isOpened=dto.getIsOpened();

            afterSchoolResponses.add(new AfterSchoolResponse(id,title,List.of(dayOfWeek),List.of(grade),isOpened));
        });
        return afterSchoolResponses;
    }
}
