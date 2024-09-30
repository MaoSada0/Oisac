package ru.qq.database.postgres_db.entity;


import jakarta.persistence.*;
import lombok.*;
import ru.qq.database.postgres_db.entity.serializable.TeacherCatalogId;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_teacher_catalog", schema = "oisac")
@IdClass(TeacherCatalogId.class)
public class TeacherCatalog {

    @Id
    @Column(name = "c_teacher_id")
    private Long teacherId;

    @Id
    @Column(name = "c_catalog_id")
    private Long catalogId;
}
