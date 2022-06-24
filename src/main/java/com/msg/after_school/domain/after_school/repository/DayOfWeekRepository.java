package com.msg.after_school.domain.after_school.repository;

import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayOfWeekRepository extends JpaRepository<DayOfWeek,Long> {
    List<DayOfWeek> findDayOfWeekByDayOfWeek(String dayOfWeek);

}
