package ru.qq.database.postgres_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.qq.database.postgres_db.entity.TaskCatalog;

@Repository
public interface TaskCatalogRepository extends JpaRepository<TaskCatalog, Long> {
}
