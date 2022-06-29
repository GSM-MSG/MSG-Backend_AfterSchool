package com.msg.after_school.domain.after_school.data.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class Teacher {
    @Id private String userId;
    private String password;
}
