package ru.qq.database.mongo_db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.qq.database.mongo_db.document.Teacher;

@Repository
public interface MongoDbRepository extends MongoRepository<Teacher, String> {

}
