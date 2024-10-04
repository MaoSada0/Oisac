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
@Table(name = "t_task_catalogs", schema = "oisac")
public class TaskCatalogPostgres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_catalog_id")
    private Long catalogId;

    @Column(name = "c_catalog_name", nullable = false, unique = true)
    private String catalogName;

    @OneToMany(mappedBy = "taskCatalogPostgres", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaskPostgres> taskPostgres;

}
