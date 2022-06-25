package com.msg.after_school.domain.after_school.data.entity;

import com.msg.after_school.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
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

