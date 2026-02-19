package com.example.Study.System;


import com.example.Study.System.dao.TeacherRepository;
import com.example.Study.System.model.TeacherEntity;
import com.example.Study.System.model.dto.TeacherDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

public class TeacherControllerTest extends StudySystemApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TeacherRepository teacherRepository;

    @BeforeEach
    void cleanDb() {
        teacherRepository.deleteAll();
    }

    @Test
    void createTeacherSuccessfully() throws Exception {
        TeacherDto validTeacherDto = new TeacherDto("Иван", "Петров");
        int initialSize = teacherRepository.findAll().size();

        ResponseEntity<TeacherDto> response = restTemplate.postForEntity(
                "/api/v1/lms/teachers/",
                validTeacherDto,
                TeacherDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().name()).isEqualTo("Иван");
        assertThat(response.getBody().surname()).isEqualTo("Петров");

        assertThat(teacherRepository.findAll()).hasSize(initialSize + 1);
    }

    @Test
    void createTeacherWithInvalidParameters_400() throws Exception {
        TeacherDto inValidTeacherDto = new TeacherDto("", null);

        ResponseEntity<TeacherDto> response = restTemplate.postForEntity(
                "/api/v1/lms/teachers/",
                inValidTeacherDto,
                TeacherDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void updateTeacherSuccessfully() throws Exception {
        TeacherEntity teacherEntity =
        teacherRepository.save(TeacherEntity.builder()
                .name("Иван")
                .surname("Петров")
                .build());

        String url = "/api/v1/lms/teachers/" + teacherEntity.getId();

        TeacherDto updateDto = new TeacherDto("Антон", "Чигур");

        ResponseEntity<TeacherDto> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(updateDto),
                TeacherDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().name()).isEqualTo(updateDto.name());
        assertThat(response.getBody().surname()).isEqualTo(updateDto.surname());
    }

    @Test
    void updateTeacherWithInvalidParameters_400() throws Exception {
        TeacherEntity teacherEntity =
                teacherRepository.save(TeacherEntity.builder()
                        .name("Иван")
                        .surname("Петров")
                        .build());

        String url = "/api/v1/lms/teachers/" + teacherEntity.getId();

        TeacherDto updateDto = new TeacherDto("", null);

        ResponseEntity<TeacherDto> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(updateDto),
                TeacherDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void updateTeacher_NotFoundId() throws Exception {
        TeacherDto updateDto = new TeacherDto("Антон", "Чигур");

        String url = "/api/v1/lms/teachers/123";

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(updateDto),
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteTeacherSuccessfully() throws Exception {
        TeacherEntity teacherEntity =
                teacherRepository.save(TeacherEntity.builder()
                        .name("Валерий")
                        .surname("Жмышенко")
                        .build());

        String url = "/api/v1/lms/teachers/" + teacherEntity.getId();
        int endSize = teacherRepository.findAll().size() - 1;

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        List<TeacherEntity> teachers = teacherRepository.findAll();
        assertThat(teachers).hasSize(endSize);
        Assertions.assertTrue(teacherRepository.findById(teacherEntity.getId()).isEmpty());
    }

    @Test
    void deleteTeacher_NotFound() throws Exception {
        String url = "/api/v1/lms/teachers/123";

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
