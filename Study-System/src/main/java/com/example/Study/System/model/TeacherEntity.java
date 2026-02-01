package com.example.Study.System.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "study_system", name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TeacherEntity extends AbstractEntity {

    private String name;
    private String surname;
}
