package ru.qq.database.mongo_db.document.student;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Data
public class StudentMongo {

    @Id
    private String nicknameOfStudent;

    @Field("Fullname")
    private String fullname;

    @Field("Task-catalogs")
    private List<TaskCatalogForStudentMongo> taskCatalogs;

    @Field("Teacher")
    private List<String> teacherIds;
}
