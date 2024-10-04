package ru.qq.database.postgres_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.qq.database.postgres_db.entity.StudentPostgres;

import java.util.Optional;

@Repository
public interface PostgresStudentRepository extends JpaRepository<StudentPostgres, Long> {

    //@Query(value = "SELECT EXISTS(SELECT 1 FROM oisac.t_students WHERE c_student_nickname = :nickname)", nativeQuery = true)
    boolean existsByStudentNickname(String studentNickname);//@Param("nickname")

    //@Query(value = "SELECT * FROM oisac.t_students WHERE c_student_nickname = :nickname", nativeQuery = true)
    Optional<StudentPostgres> findByStudentNickname(String studentNickname);//@Param("nickname")

}
