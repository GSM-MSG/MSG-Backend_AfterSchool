package com.msg.after_school.repository;

import com.msg.after_school.domain.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DayOfWeekRepository extends JpaRepository<DayOfWeek,Long> {
    List<DayOfWeek> findAllByDayOfWeek(String week);
}
