package com.msg.after_school.domain.after_school.dao;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.exception.AfterSchoolNotFoundException;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
//TODO 인터페이스로 바꾸기 (안에 있는 구현체는 AfterSchoolDaoImpl)
public class AfterSchoolDao {
    private final AfterSchoolRepository afterSchoolRepository;

    public AfterSchool getAfterSchoolByAfterSchoolId(Integer id){
        return afterSchoolRepository.findById(id).orElseThrow(AfterSchoolNotFoundException::new) ;
    }

    public List<AfterSchool> findAllByIsOpened(boolean isOpened) {
        return afterSchoolRepository.findAllByIsOpened (isOpened);
    }
}
