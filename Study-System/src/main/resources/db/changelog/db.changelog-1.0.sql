--changeset bogdan:1
CREATE TABLE courses
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(32) NOT NULL,
    description TEXT DEFAULT ''
);

CREATE TABLE students
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL,
    group_id INT NOT NULL,
    CONSTRAINT fk_students_groups
        FOREIGN KEY (group_id)
        REFERENCES groups(id)
);

CREATE TABLE groups
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(16) NOT NULL
);

CREATE TABLE teachers
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL
);

CREATE TABLE schedules
(
    id SERIAL PRIMARY KEY,
    group_id INT NOT NULL,
    teacher_id INT NOT NULL,
    course_id INT NOT NULL,
    lesson_date DATE NOT NULL,

    CONSTRAINT fk_schedules_groups
        FOREIGN KEY (group_id)
            REFERENCES groups(id),

    CONSTRAINT fk_schedules_teachers
        FOREIGN KEY (teacher_id)
            REFERENCES teachers(id),

    CONSTRAINT fk_schedules_courses
        FOREIGN KEY (course_id)
            REFERENCES courses(id)
);
--changeset bogdan:2
ALTER TABLE schedules ALTER COLUMN lesson_date TYPE TIMESTAMP WITHOUT TIME ZONE;
