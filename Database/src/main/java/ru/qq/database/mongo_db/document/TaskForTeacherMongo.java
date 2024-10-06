package ru.qq.database.mongo_db.document;

import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Data
public class TaskForTeacherMongo {

    @Id
    private String Id;

    @Field("Description")
    private String description;

    @Field("Answer")
    private String answer;
}
