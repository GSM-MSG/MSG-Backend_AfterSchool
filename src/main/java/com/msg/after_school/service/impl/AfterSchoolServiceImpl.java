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
        List<AfterSchool> filteredList = Arrays.asList(
                applyFilter(searchConditionDto, afterSchoolList)
            );
//        Long grade = searchConditionDto.getGrade();
//        SeasonType season = searchConditionDto.getSeason();
//        String week = searchConditionDto.getWeek();
//        List<DayOfWeek> dayOfWeek = dayOfWeekRepository.findAllByDayOfWeek(week);
//        List<Grade> gradeData = gradeRepository.findAllByGrade(grade;
//        List<AfterSchool> data = afterSchoolRepository.findAllByGradeAndDayOfWeekAndSeason(gradeData,dayOfWeek,season);
        return filteredList;
    }

    private AfterSchool[] applyFilter(SearchConditionDto searchConditionDto, List<AfterSchool> afterSchoolList) {
        return afterSchoolList.stream().filter(afterSchool -> {
            return afterSchool.getGrade().stream().map(e -> e.getGrade()).filter(g -> g.equals(searchConditionDto.getGrade())).count() != 0 &&
                    afterSchool.getSeason().equals(searchConditionDto.getSeason()) &&
                    searchConditionDto.getWeek().equals("ALL") ? afterSchool.getDayOfWeek().stream().map(e -> e.getDayOfWeek()).filter(w -> w.equals("MON") || w.equals("TUE") || w.equals("WED")).count() != 0
                    : afterSchool.getDayOfWeek().stream().map(e -> e.getDayOfWeek()).filter(w -> w.equals(searchConditionDto.getWeek())).count() != 0;

        }).toArray(AfterSchool[]::new);
    }
}
