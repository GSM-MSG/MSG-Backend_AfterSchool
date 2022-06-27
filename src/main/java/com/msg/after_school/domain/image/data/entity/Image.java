package com.msg.after_school.domain.image.data.entity;

import com.msg.after_school.domain.club.data.entity.Club;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "clubId")
    private Club club;

    @Column
    private String url;
}
