package ru.qq.database.postgres_db.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.qq.database.postgres_db.entity.serializable.StudentTeacherId;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_student_teacher", schema = "oisac")
@IdClass(StudentTeacherId.class)
public class StudentTeacher {

    @Id
    @Column(name = "c_student_id")
    private Long studentId;

    @Id
    @Column(name = "c_teacher_id")
    private Long teacherId;
}
