package com.msg.after_school.domain.after_school.repository;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.data.type.SeasonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AfterSchoolRepository extends JpaRepository<AfterSchool,Long> {

    List<AfterSchool> findAllByGradeAndDayOfWeekAndSeason(Integer grade, DayOfWeek dayOfWeek, SeasonType season);

    AfterSchool findByAfterSchoolId(Long afterSchoolId);
}
