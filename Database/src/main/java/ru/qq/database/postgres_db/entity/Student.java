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
@Table(name = "t_students", schema = "oisac")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_student_id")
    private Long studentId;

    @Column(name = "c_student_name", nullable = false)
    private String studentName;
}
