CREATE SCHEMA if not exists oisac;

CREATE TABLE oisac.t_teachers(
                                 c_teacher_id SERIAL PRIMARY KEY,
                                 c_teacher_name VARCHAR(255) NOT NULL
);

CREATE TABLE oisac.t_students (
                                  c_student_id SERIAL PRIMARY KEY,
                                  c_student_name VARCHAR(255) NOT NULL
);

CREATE TABLE oisac.t_task_catalogs (
                                       c_catalog_id SERIAL PRIMARY KEY,
                                       c_catalog_name VARCHAR(255) NOT NULL
);

CREATE TABLE oisac.t_tasks (
                               c_task_id SERIAL PRIMARY KEY,
                               c_catalog_id INT REFERENCES t_task_catalogs(c_catalog_id) ON DELETE CASCADE,
                               c_description TEXT NOT NULL,
                               c_answer TEXT NOT NULL
);

CREATE TABLE oisac.t_student_teacher (
                                         c_student_id INT REFERENCES t_students(c_student_id) ON DELETE CASCADE,
                                         c_teacher_id INT REFERENCES t_teachers(c_teacher_id) ON DELETE CASCADE,
                                         PRIMARY KEY (c_student_id, c_teacher_id)
);

CREATE TABLE oisac.t_student_catalog (
                                         c_student_id INT REFERENCES t_students(c_student_id) ON DELETE CASCADE,
                                         c_catalog_id INT REFERENCES t_task_catalogs(c_catalog_id) ON DELETE CASCADE,
                                         PRIMARY KEY (c_student_id, c_catalog_id)
);

CREATE TABLE oisac.t_teacher_catalog (
                                         c_teacher_id INT REFERENCES t_teachers(c_teacher_id) ON DELETE CASCADE,
                                         c_catalog_id INT REFERENCES t_task_catalogs(c_catalog_id) ON DELETE CASCADE,
                                         PRIMARY KEY (c_teacher_id, c_catalog_id)
);