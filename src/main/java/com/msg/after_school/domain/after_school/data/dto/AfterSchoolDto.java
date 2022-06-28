package com.msg.after_school.domain.after_school.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msg.after_school.domain.after_school.data.entity.ClassRegistration;
import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.data.entity.Grade;
import com.msg.after_school.domain.after_school.data.type.SeasonType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AfterSchoolDto {
    private Integer id;

    private String title;

    private List<Grade> grade;

    private List<DayOfWeek> dayOfWeek;

    private String teacher;

    @Enumerated(EnumType.STRING)
    private SeasonType season;

    private Integer yearOf;

    private Boolean isOpened;

    private Set<ClassRegistration> classRegistration = new HashSet<>();

    public void changeIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }
}
