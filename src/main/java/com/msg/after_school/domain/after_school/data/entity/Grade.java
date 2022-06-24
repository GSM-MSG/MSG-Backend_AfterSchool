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
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "afterSchool_id")
    private AfterSchool afterSchool;

    private Long grade;

    public void mapping(AfterSchool afterSchool){
        this.afterSchool=afterSchool;
    }
}
