package ru.qq.database.postgres_db.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_teachers", schema = "oisac")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_teacher_id")
    private Long teacherId;

    @Column(name = "c_teacher_name", nullable = false)
    private String teacherName;
}
