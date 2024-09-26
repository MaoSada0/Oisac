package ru.qq.database.mongo_db.document;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Teacher {
    @Id
    private String id;

    @Field("Name")
    private String nameOfTeacher;

    @Field("Task-catalogs")
    private List<TaskCatalog> taskCatalogs;
}
