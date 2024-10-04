package ru.qq.database.mongo_db.document;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Data
public class TaskMongo {
    @Field("Description")
    private String description;

    @Field("Answer")
    private String answer;
}
