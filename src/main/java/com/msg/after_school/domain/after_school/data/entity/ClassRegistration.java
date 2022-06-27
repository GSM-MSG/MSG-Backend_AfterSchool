package com.msg.after_school.domain.after_school.data.entity;

import com.msg.after_school.domain.user.data.entity.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ClassRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "afterschoolId")
    private AfterSchool afterSchool;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usersEmail")
    private User user;
}

