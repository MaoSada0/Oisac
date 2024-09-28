package ru.qq.database.mongo_db.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.qq.database.mongo_db.document.Teacher;

import java.util.List;

@Repository
public interface MongoDbRepository extends MongoRepository<Teacher, String> {

    @Aggregation(pipeline = {
            "{ '$match': { '_id': ?0 } }",                        // Находим документ по _id
            "{ '$unwind': '$Task-catalogs' }",                    // Разворачиваем массив Task-catalogs
            "{ '$project': { 'name': '$Task-catalogs.Name' } }"   // Извлекаем поле Name
    })
    List<String> findTaskCatalogNamesByTeacherId(String nameOfTeacher);

    @Query(value = "{ '_id': ?0, 'Task-catalogs.Name': ?1 }", exists = true)
    boolean existsByIdAndTaskCatalogName(String teacherId, String taskCatalogName);

}
