package com.msg.after_school.domain.club.data.entity;

import com.msg.after_school.global.enums.ClubType;
import com.msg.after_school.domain.image.data.entity.Image;
import com.msg.after_school.domain.member.data.entity.Member;

import javax.persistence.*;
import java.util.List;

@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private ClubType type;

    @Column
    private String bannerUrl;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String contact;

    @Column(nullable = false)
    private String teacher;

    @Column
    private Boolean isOpened;

    @Column
    private String notionLink;

    @OneToMany(mappedBy = "club", cascade = CascadeType.REMOVE)
    private List<Member> member;

    @OneToMany(mappedBy = "club", cascade = CascadeType.REMOVE)
    private List<Image> activityUrls;


}
