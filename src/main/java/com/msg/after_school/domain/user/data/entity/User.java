package com.msg.after_school.domain.user.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "user") // 나중에 본 DB 연결 할때 지워야됨
public class User {
    @Id
    private String email;
    private String name;
    private Integer grade;
    @Column(name = "class")
    private Integer class_;
    private Integer num;
    private String userImg;
    @Column(nullable = false)
    private String refreshToken;
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
