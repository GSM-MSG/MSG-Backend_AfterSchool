package com.msg.after_school.service.impl;

import com.msg.after_school.domain.AfterSchool;
import com.msg.after_school.domain.DayOfWeek;
import com.msg.after_school.dto.AfterSchoolDto;
import com.msg.after_school.dto.SearchConditionDto;
import com.msg.after_school.repository.AfterSchoolRepository;
import com.msg.after_school.service.AfterSchoolService;
import com.msg.after_school.type.SeasonType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AfterSchoolServiceImpl implements AfterSchoolService {

    private final AfterSchoolRepository afterSchoolRepository;

    @Override
    @Transactional
    public List<AfterSchoolDto> findAfterSchoolListBySearchCondition(SearchConditionDto searchConditionDto) {
        Integer grade = searchConditionDto.getGrade();
        SeasonType season = searchConditionDto.getSeason();
        DayOfWeek dayOfWeek = searchConditionDto.getWeek();
        List<AfterSchool> Data = afterSchoolRepository.findAllByGradeAndAndDayOfWeekAndSeason(grade,season,dayOfWeek);
        return null;
    }
}
