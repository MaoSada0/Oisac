package ru.qq.database.mongo_db.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.qq.database.mongo_db.document.student.StudentMongo;

import java.util.List;

@Repository
public interface MongoStudentRepository extends MongoRepository<StudentMongo, String> {

    @Aggregation(pipeline = {
            "{ '$match': { '_id': ?0 } }",
            "{ '$unwind': '$Task-catalogs' }",
            "{ '$project': { 'name': '$Task-catalogs.Name' } }"
    })
    List<String> findTaskCatalogNamesByStudentId(String nameOfStudent);

    @Query(value = "{ '_id': ?0, 'Task-catalogs._id': ?1 }", exists = true)
    boolean existsByStudentIdAndTaskCatalogId(String studentId, String taskCatalogName);
}
