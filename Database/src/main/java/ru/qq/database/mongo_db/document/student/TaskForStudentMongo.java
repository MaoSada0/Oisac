package ru.qq.database.mongo_db.document.student;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Data
public class TaskForStudentMongo {
    @Id
    private String Id;

    @Field("Description")
    private String description;

    @Field("Right-answer")
    private String rightAnswer;

    @Field("Answer-of-student")
    private String answerOfStudent;

}
