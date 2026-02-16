--changeset bogdan:1
CREATE SCHEMA study_system;

CREATE TABLE study_system.courses
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(32) NOT NULL,
    description TEXT DEFAULT ''
);

CREATE TABLE study_system.groups
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(16) NOT NULL
);

CREATE TABLE study_system.students
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL,
    group_id INT NOT NULL,
    CONSTRAINT fk_students_groups
        FOREIGN KEY (group_id)
        REFERENCES study_system.groups(id)
);



CREATE TABLE study_system.teachers
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL
);

CREATE TABLE study_system.schedules
(
    id SERIAL PRIMARY KEY,
    group_id INT NOT NULL,
    teacher_id INT NOT NULL,
    course_id INT NOT NULL,
    lesson_date DATE NOT NULL,

    CONSTRAINT fk_schedules_groups
        FOREIGN KEY (group_id)
            REFERENCES study_system.groups(id),

    CONSTRAINT fk_schedules_teachers
        FOREIGN KEY (teacher_id)
            REFERENCES study_system.teachers(id),

    CONSTRAINT fk_schedules_courses
        FOREIGN KEY (course_id)
            REFERENCES study_system.courses(id)
);
--changeset bogdan:2
ALTER TABLE study_system.schedules ALTER COLUMN lesson_date TYPE TIMESTAMP WITHOUT TIME ZONE;

--changeset bogdan:3
CREATE TABLE study_system.groups_courses
(
    id        SERIAL PRIMARY KEY,
    group_id  INT NOT NULL,
    course_id INT NOT NULL,

    CONSTRAINT fk_groups_courses
        FOREIGN KEY (group_id)
            REFERENCES study_system.groups (id),
    CONSTRAINT fk_courses_groups
        FOREIGN KEY (course_id)
            REFERENCES study_system.courses (id)

);