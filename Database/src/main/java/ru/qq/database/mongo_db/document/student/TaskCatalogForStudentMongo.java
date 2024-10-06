package ru.qq.database.mongo_db.document.student;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.qq.database.mongo_db.document.TaskForTeacherMongo;

import java.util.List;


@Document
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Data
public class TaskCatalogForStudentMongo {

    @Id
    private String Id;

    @Field("Name-of-task-catalog")
    private String name;

    @Field("Tasks")
    private List<TaskForStudentMongo> tasks;

    @Field("Is-done")
    private Boolean isDone;

    @Field("Is-done-with-mistakes")
    private Boolean isDoneWithMistakes;

    @Field("Attempts")
    private List<AttemptForTaskCatalogMongo> attempts;

}
