package ru.qq.database.postgres_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.qq.database.postgres_db.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
