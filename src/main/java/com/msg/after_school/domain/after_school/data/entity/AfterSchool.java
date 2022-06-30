package com.msg.after_school.domain.after_school.data.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msg.after_school.domain.after_school.data.type.SeasonType;
import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "afterSchool")
public class AfterSchool {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @OneToMany(mappedBy = "afterSchool")
    private List<Grade> grade;
    @OneToMany(mappedBy = "afterSchool")
    private List<DayOfWeek> dayOfWeek;
    private String teacher;
    @Enumerated(EnumType.STRING)
    private SeasonType season;
    private Integer yearOf;
    @Column(columnDefinition = "TINYINT")
    private Boolean isOpened;
}
