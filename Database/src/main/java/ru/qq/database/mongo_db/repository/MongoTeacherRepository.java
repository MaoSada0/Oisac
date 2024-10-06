package ru.qq.database.mongo_db.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.qq.database.mongo_db.document.TaskCatalogForTeacherMongo;
import ru.qq.database.mongo_db.document.TeacherMongo;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongoTeacherRepository extends MongoRepository<TeacherMongo, String> {

    @Aggregation(pipeline = {
            "{ '$match': { '_id': ?0 } }",
            "{ '$unwind': '$Task-catalogs' }",
            "{ '$project': { 'name': '$Task-catalogs.Name' } }"
    })
    List<String> findTaskCatalogNamesByTeacherId(String nameOfTeacher);

    @Query(value = "{ '_id': ?0, 'Task-catalogs._id': ?1 }", exists = true)
    boolean existsByTeacherIdAndTaskCatalogId(String teacherId, String taskCatalogId);

    @Aggregation(pipeline = {
            "{ '$match': { '_id': ?0 } }",
            "{ '$unwind': '$Task-catalogs' }",
            "{ '$match': { 'Task-catalogs._id': ?1 } }",
            "{ '$replaceRoot': { 'newRoot': '$Task-catalogs' } }"
    })
    Optional<TaskCatalogForTeacherMongo> findTaskCatalogByTeacherIdAndCatalogId(String teacherId, String catalogId);

}
