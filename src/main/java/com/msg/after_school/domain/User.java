package com.msg.after_school.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "users") // 나중에 본 DB 연결 할때 지워야됨
public class User {
    @Id
    private String email;

    private String name;

    private int grade;

    private int class_;

    private int num;
}
