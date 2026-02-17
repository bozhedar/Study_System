package com.example.Study.System;

import com.example.Study.System.dao.TeacherRepository;
import com.example.Study.System.model.dto.TeacherDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeacherControllerTest extends StudySystemApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    private ObjectMapper objectMapper = new ObjectMapper();


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

//    @Test
//    void createTeacherWithInvalidParameters_400() throws Exception {
//        TeacherDto validTeacherDto = new TeacherDto("", null);
//
//        mockMvc.perform(post("/api/v1/lms/teachers/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(validTeacherDto)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void updateTeacherSuccessfully() throws Exception {
//        TeacherEntity teacherEntity =
//        teacherRepository.save(TeacherEntity.builder()
//                .name("Иван")
//                .surname("Петров")
//                .build());
//
//        Long id = teacherEntity.getId();
//
//        TeacherDto updateDto = new TeacherDto("Антон", "Чигур");
//
//        mockMvc.perform(put("/api/v1/lms/teachers/" + id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updateDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value(updateDto.name()))
//                .andExpect(jsonPath("$.surname").value(updateDto.surname()));
//    }
//
//    @Test
//    void updateTeacherWithInvalidParameters_400() throws Exception {
//        TeacherEntity teacherEntity =
//                teacherRepository.save(TeacherEntity.builder()
//                        .name("Иван")
//                        .surname("Петров")
//                        .build());
//
//        Long id = teacherEntity.getId();
//
//        TeacherDto updateDto = new TeacherDto("", null);
//
//        mockMvc.perform(put("/api/v1/lms/teachers/" + id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updateDto)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void updateTeacher_NotFoundId() throws Exception {
//        Long id = 123L;
//
//        TeacherDto updateDto = new TeacherDto("Антон", "Чигур");
//
//        mockMvc.perform(put("/api/v1/lms/teachers/" + id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updateDto)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void deleteTeacherSuccessfully() throws Exception {
//        TeacherEntity teacherEntity =
//                teacherRepository.save(TeacherEntity.builder()
//                        .name("Валерий")
//                        .surname("Жмышенко")
//                        .build());
//        Long id = teacherEntity.getId();
//        int endSize = teacherRepository.findAll().size() - 1;
//
//        mockMvc.perform((delete("/api/v1/lms/teachers/" + id)));
//
//        List<TeacherEntity> teachers = teacherRepository.findAll();
//        assertThat(teachers).hasSize(endSize);
//    }
//
//    @Test
//    void deleteTeacher_NotFound() throws Exception {
//        mockMvc.perform((delete("/api/v1/lms/teachers/3")))
//                .andExpect(status().isNotFound());
//    }
}
