package ru.qq.database.mongo_db.document.student;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;


@Document
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Data
public class AttemptForTaskCatalogMongo {

    @Id
    private Integer id;

    @Field("Is-done-right")
    private Map<String, Boolean> rightAnswers; // task id -> true/false
}
