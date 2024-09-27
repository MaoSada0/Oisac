package ru.qq.database.mongo_db.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Data
public class TaskCatalog {

    @Field("Name")
    private String name;

    @Field("Tasks")
    private List<Task> tasks;
}