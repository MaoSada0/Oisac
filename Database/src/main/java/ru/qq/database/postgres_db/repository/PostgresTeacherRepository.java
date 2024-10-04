package ru.qq.database.postgres_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.qq.database.postgres_db.entity.TeacherPostgres;

import java.util.Optional;

@Repository
public interface PostgresTeacherRepository extends JpaRepository<TeacherPostgres, Long> {

    //@Query(value = "SELECT EXISTS(SELECT 1 FROM oisac.t_teachers WHERE c_teacher_nickname = :nickname)", nativeQuery = true)
    boolean existsByTeacherNickname(String teacherNickname); //@Param("nickname")

    //@Query(value = "SELECT * FROM oisac.t_teachers WHERE c_teacher_nickname = :nickname", nativeQuery = true)
    Optional<TeacherPostgres> findByTeacherNickname(String teacherNickname); //@Param("nickname")
}
