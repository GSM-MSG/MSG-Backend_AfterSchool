package com.msg.after_school.domain.after_school.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grade")
public class Grade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "afterSchoolId")
    private AfterSchool afterSchool;
    private Integer grade;
}
