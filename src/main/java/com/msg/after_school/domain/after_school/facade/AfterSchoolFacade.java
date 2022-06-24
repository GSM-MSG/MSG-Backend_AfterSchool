package com.msg.after_school.domain.after_school.facade;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.after_school.repository.AfterSchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AfterSchoolFacade {
    private final AfterSchoolRepository afterSchoolRepository;

    public Optional<AfterSchool> getAfterSchoolByAfterSchoolId(Long afterSchoolId){
        return afterSchoolRepository.findById(afterSchoolId);
    }
}
