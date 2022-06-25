package com.msg.after_school.domain.after_school.repository;

import com.msg.after_school.domain.after_school.data.entity.ClassRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassRegistrationRepository extends JpaRepository<ClassRegistration,Long> {
    @Query("select c from ClassRegistration c join fetch c.afterSchool")
    List<ClassRegistration> findAll();

}
