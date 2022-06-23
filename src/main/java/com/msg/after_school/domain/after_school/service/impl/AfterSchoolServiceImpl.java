package com.msg.after_school.domain.after_school.service.impl;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.data.dto.AfterSchoolDto;
import com.msg.after_school.domain.after_school.data.dto.SearchConditionDto;
import com.msg.after_school.domain.after_school.service.AfterSchoolService;
import com.msg.after_school.domain.after_school.data.type.SeasonType;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRepository;
import com.msg.after_school.domain.after_school.repository.DayOfWeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AfterSchoolServiceImpl implements AfterSchoolService {

    @Autowired
    private final AfterSchoolRepository afterSchoolRepository;
    @Autowired
    private final DayOfWeekRepository dayOfWeekRepository;

    @Override
    @Transactional
    public List<AfterSchoolDto> findAfterSchoolListBySearchCondition(SearchConditionDto searchConditionDto) {
        Integer grade = searchConditionDto.getGrade();
        SeasonType season = searchConditionDto.getSeason();
        DayOfWeek dayOfWeek = searchConditionDto.getWeek();
        List<AfterSchool> data = afterSchoolRepository.findAllByGradeAndDayOfWeekAndSeason(grade,dayOfWeek,season);
        return null;
    }
}
