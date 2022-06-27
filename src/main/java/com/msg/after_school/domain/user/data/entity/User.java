package com.msg.after_school.domain.user.data.entity;

import com.msg.after_school.domain.after_school.data.entity.ClassRegistration;
import com.msg.after_school.domain.member.data.entity.Member;
import com.msg.after_school.domain.requestJoin.data.entity.RequestJoin;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//@Table(name = "users") // 나중에 본 DB 연결 할때 지워야됨
public class User {
    @Id
    private String email;

    private String name;

    private Long grade;

    @Column(name = "class")
    private Long class_;

    private Long num;

    @Column
    private String userImg;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Member> member;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<RequestJoin> requestJoin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ClassRegistration> classRegistration;

    @Column(nullable = false)
    private String refreshToken;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
