package com.msg.after_school.domain.teacher.data.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Teacher {
    @Id
    private Long userId;

    @Column
    private String password;
}
