package com.msg.after_school.domain.after_school.dao.impl;

import com.msg.after_school.domain.after_school.dao.AfterSchoolDao;
import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.exception.AfterSchoolNotFoundException;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AfterSchoolDaoImpl implements AfterSchoolDao {
    private final AfterSchoolRepository afterSchoolRepository;

    public AfterSchool getAfterSchoolByAfterSchoolId(Integer id){
        return afterSchoolRepository.findById(id).orElseThrow(AfterSchoolNotFoundException::new) ;
    }

    public List<AfterSchool> findAllByIsOpened(boolean isOpened) {
        return afterSchoolRepository.findAllByIsOpened (isOpened);
    }
}
