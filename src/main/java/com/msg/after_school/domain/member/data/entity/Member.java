package com.msg.after_school.domain.member.data.entity;

import com.msg.after_school.domain.club.data.entity.Club;
import com.msg.after_school.domain.user.data.entity.User;
import com.msg.after_school.global.enums.MemberScope;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userEmail")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "culbId")
    private Club club;

    @Enumerated(EnumType.STRING)
    private MemberScope scope;
}
