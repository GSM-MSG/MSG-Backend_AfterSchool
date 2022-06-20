package com.msg.after_school.repository;

import com.msg.after_school.domain.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayOfWeekRepository extends JpaRepository<DayOfWeek,Long> {
    List<DayOfWeek> findDayOfWeekByDayOfWeek(String dayOfWeek);

}
