package ru.qq.database.mongo_db.document;

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
public class Teacher {

    @Id
    private String nameOfTeacher;

    @Field("Task-catalogs")
    private List<TaskCatalog> taskCatalogs;
}
