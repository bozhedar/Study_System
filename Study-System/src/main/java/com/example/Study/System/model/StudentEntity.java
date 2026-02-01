package com.example.Study.System.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(schema = "study_system", name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StudentEntity extends AbstractEntity{

    private String name;
    private String surname;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;
}
