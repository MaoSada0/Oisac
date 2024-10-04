package ru.qq.database.postgres_db.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = "students")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_teachers", schema = "oisac")
public class TeacherPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_teacher_id")
    private Long teacherId;

    @Column(name = "c_teacher_nickname", nullable = false, unique = true)
    private String teacherNickname;

    @Column(name = "c_teacher_fullname", nullable = false)
    private String teacherFullname;


    @ManyToMany
    @JoinTable(
            name = "t_student_teacher",
            joinColumns = @JoinColumn(name = "c_teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "c_student_id")
    )
    private Set<StudentPostgres> students;
}
