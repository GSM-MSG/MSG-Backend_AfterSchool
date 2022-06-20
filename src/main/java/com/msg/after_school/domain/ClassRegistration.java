package com.msg.after_school.domain;

import com.msg.after_school.domain.user.entity.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ClassRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "afterschool_id")
    private AfterSchool afterSchool;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_email")
    private User user;
}

