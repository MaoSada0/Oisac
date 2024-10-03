package ru.qq.database.postgres_db.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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

    @Column(name = "c_student_nickname", nullable = false, unique = true)
    private String studentNickname;

    @Column(name = "c_student_fullname", nullable = false)
    private String studentFullname;


    @ManyToMany
    @JoinTable(
            name = "t_student_catalog",
            joinColumns = @JoinColumn(name = "c_student_id"),
            inverseJoinColumns = @JoinColumn(name = "c_catalog_id")
    )
    private Set<TaskCatalog> catalogs;
}
