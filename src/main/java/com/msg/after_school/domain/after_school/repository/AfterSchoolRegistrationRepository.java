package com.msg.after_school.domain.after_school.repository;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.data.entity.ClassRegistration;
import com.msg.after_school.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AfterSchoolRegistrationRepository extends JpaRepository<ClassRegistration,Long> {
    @Query("select distinct c from ClassRegistration c join fetch c.afterSchool")
    List<ClassRegistration> findAllJoinFetch();

    Boolean existsByUserAndAfterSchool(User users_email, AfterSchool afterschool_id);

}
