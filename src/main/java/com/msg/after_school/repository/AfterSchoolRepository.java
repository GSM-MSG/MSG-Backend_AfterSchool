package com.msg.after_school.repository;

import com.msg.after_school.domain.AfterSchool;
import com.msg.after_school.domain.DayOfWeek;
import com.msg.after_school.domain.Grade;
import com.msg.after_school.type.SeasonType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AfterSchoolRepository extends JpaRepository<AfterSchool,Long> {
    List<AfterSchool> findAllByGradeAndAndDayOfWeekAndSeason(Grade grade, DayOfWeek dayOfWeek, SeasonType season);
}
