package com.msg.after_school.domain.after_school.dao;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;

import java.util.List;

public interface AfterSchoolDao {
    AfterSchool getAfterSchoolByAfterSchoolId(Integer id);

    List<AfterSchool> findAllByIsOpened(boolean isOpened);
}
