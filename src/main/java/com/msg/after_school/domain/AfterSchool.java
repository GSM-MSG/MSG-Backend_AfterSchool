package com.msg.after_school.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msg.after_school.domain.ClassRegistration;
import com.msg.after_school.type.SeasonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AfterSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

//    private Long personnel;

    @OneToMany(mappedBy = "afterSchool")
    private List<Grade> grade;

    @OneToMany(mappedBy = "afterSchool")
    private List<DayOfWeek> dayOfWeek;

    private String teacher;

    @Column(columnDefinition = "TINYINT")
    private Boolean canDuplicate;

    @Enumerated(EnumType.STRING)
    private SeasonType season;

    private Long yearOf;

    @Column(columnDefinition = "TINYINT")
    private Boolean isOpened;

    @JsonIgnore
    @OneToMany(mappedBy = "afterSchool", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<ClassRegistration> classRegistration = new HashSet<>();

    public void changeIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }
}
