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
@Table(name = "t_tasks", schema = "oisac")
public class TaskPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_task_id")
    private Long taskId;

    @ManyToOne
    @JoinColumn(name = "c_catalog_id", nullable = false)
    private TaskCatalogPostgres taskCatalogPostgres;

    @Column(nullable = false, name = "c_description", unique = true)
    private String description;

    @Column(nullable = false, name = "c_answer")
    private String answer;
}
