package com.msg.after_school.domain.after_school.data.entity;

import com.msg.after_school.domain.user.data.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "class_registration")
public class AfterSchoolRegistration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "afterschoolId")
    private AfterSchool afterSchool;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userEmail")
    private User user;
}

