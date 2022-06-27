package com.msg.after_school.domain.after_school.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class DayOfWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "afterSchoolId")
    private AfterSchool afterSchool;

    private String dayOfWeek;

    public void mapping(AfterSchool afterSchool){
        this.afterSchool=afterSchool;
    }
}
