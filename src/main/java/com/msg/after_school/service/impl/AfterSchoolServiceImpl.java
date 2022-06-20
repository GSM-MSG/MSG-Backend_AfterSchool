package com.msg.after_school.service.impl;

import com.msg.after_school.dto.AfterSchoolDto;
import com.msg.after_school.dto.SearchConditionDto;
import com.msg.after_school.repository.AfterSchoolRepository;
import com.msg.after_school.service.AfterSchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AfterSchoolServiceImpl implements AfterSchoolService {

    private final AfterSchoolRepository afterSchoolRepository;

    @Override
    @Transactional
    public List<AfterSchoolDto> findAfterSchoolListBySearchCondition(SearchConditionDto dto) {
        return null;
    }
}
