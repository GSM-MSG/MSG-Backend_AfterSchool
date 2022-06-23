package com.msg.after_school.service.impl;

import com.msg.after_school.domain.AfterSchool;
import com.msg.after_school.domain.DayOfWeek;
import com.msg.after_school.domain.Grade;
import com.msg.after_school.dto.SearchConditionDto;
import com.msg.after_school.error.DayOfWeekNotFoundException;
import com.msg.after_school.error.GradeNotFoundException;
import com.msg.after_school.repository.AfterSchoolRepository;
import com.msg.after_school.repository.DayOfWeekRepository;
import com.msg.after_school.repository.GradeRepository;
import com.msg.after_school.service.AfterSchoolService;
import com.msg.after_school.type.SeasonType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AfterSchoolServiceImpl implements AfterSchoolService {

    private final AfterSchoolRepository afterSchoolRepository;
    private final DayOfWeekRepository dayOfWeekRepository;
    private final GradeRepository gradeRepository;

    @Override
    @Transactional
    public List<AfterSchool> findAfterSchoolListBySearchCondition(SearchConditionDto searchConditionDto) { // 1 학년 월 , 수
        List<AfterSchool> afterSchoolList = afterSchoolRepository.findAll();
        System.out.println(afterSchoolList);
        afterSchoolList.forEach(a -> log.info(String.valueOf(a.getDayOfWeek())));
        log.info(searchConditionDto.getWeek());
        List<AfterSchool> filteredList = Arrays.asList(
                afterSchoolList.stream().filter(afterSchool -> {
                    return afterSchool.getDayOfWeek().stream().map(e -> e.getDayOfWeek()).filter(w -> { log.info(w); return w != searchConditionDto.getWeek();}).count() != 0;
                }).toArray(AfterSchool[]::new)
        );

//        Long grade = searchConditionDto.getGrade();
//        SeasonType season = searchConditionDto.getSeason();
//        String week = searchConditionDto.getWeek();
//        List<DayOfWeek> dayOfWeek = dayOfWeekRepository.findAllByDayOfWeek(week);
//        List<Grade> gradeData = gradeRepository.findAllByGrade(grade;
//        List<AfterSchool> data = afterSchoolRepository.findAllByGradeAndDayOfWeekAndSeason(gradeData,dayOfWeek,season);
        return filteredList;
    }
}
