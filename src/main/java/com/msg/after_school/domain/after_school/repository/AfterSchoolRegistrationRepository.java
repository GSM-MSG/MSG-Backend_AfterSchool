package com.msg.after_school.domain.after_school.repository;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.data.entity.AfterSchoolRegistration;
import com.msg.after_school.domain.user.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AfterSchoolRegistrationRepository extends JpaRepository<AfterSchoolRegistration, Integer> {
    @Query("select distinct c from AfterSchoolRegistration c join fetch c.afterSchool")
    List<AfterSchoolRegistration> findAllJoinFetch();

    Boolean existsByUserAndAfterSchool(User user, AfterSchool afterSchool);
    void deleteByUserAndAfterSchool(User user, AfterSchool afterSchool);
}
