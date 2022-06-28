package com.msg.after_school.domain.requestJoin.data.entity;

import com.msg.after_school.domain.club.data.entity.Club;
import com.msg.after_school.domain.user.data.entity.User;

import javax.persistence.*;

@Entity
public class RequestJoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "clubId")
    private Club club;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userEmail")
    private User user;
}
