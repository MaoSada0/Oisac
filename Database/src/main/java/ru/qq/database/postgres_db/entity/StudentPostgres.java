package ru.qq.database.postgres_db.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = "teachers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_students", schema = "oisac")
public class StudentPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_student_id")
    private Long studentId;

    @Column(name = "c_student_nickname", nullable = false, unique = true)
    private String studentNickname;

    @Column(name = "c_student_fullname", nullable = false)
    private String studentFullname;

    @ManyToMany(mappedBy = "students")
    private Set<TeacherPostgres> teachers;
}
