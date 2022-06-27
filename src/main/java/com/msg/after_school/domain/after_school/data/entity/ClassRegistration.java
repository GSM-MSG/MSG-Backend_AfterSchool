package com.msg.after_school.domain.after_school.data.entity;

import com.msg.after_school.domain.user.data.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor //TODO AfterSchoolRegistration으로 Rename하기
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

