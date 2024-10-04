package ru.qq.database.mongo_db.document;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Data
public class TaskCatalogMongo {

    @Field("Name")
    private String name;

    @Field("Tasks")
    private List<TaskMongo> tasks;
}