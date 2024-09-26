package ru.qq.database.mongo_db.repository;

import org.springframework.stereotype.Repository;
import ru.qq.database.mongo_db.document.Teacher;

@Repository
public interface MongoRepository extends MongoRepository<Teacher, String>  {
}
