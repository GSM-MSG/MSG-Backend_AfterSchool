package com.msg.after_school.domain.user.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String email;

    private String name;

    private int grade;

    @Column(name = "class")
    private int class_;

    private int num;
}
