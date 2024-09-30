package ru.qq.database.postgres_db.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.qq.database.postgres_db.entity.serializable.StudentCatalogId;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_student_catalog", schema = "oisac")
@IdClass(StudentCatalogId.class)
public class StudentCatalog {

    @Id
    @Column(name = "c_student_id")
    private Long studentId;

    @Id
    @Column(name = "c_catalog_id")
    private Long catalogId;
}
