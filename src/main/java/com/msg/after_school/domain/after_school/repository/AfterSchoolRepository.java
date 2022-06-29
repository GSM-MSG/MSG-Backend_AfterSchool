package com.msg.after_school.domain.after_school.repository;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AfterSchoolRepository extends JpaRepository<AfterSchool, Integer> {
    List<AfterSchool> findAllByIsOpened(Boolean isOpened);
    Optional<AfterSchool> findById(Integer id);
}
