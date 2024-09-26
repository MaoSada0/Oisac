package ru.qq.database.mongo_db.document;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@Builder
public class Task {
    @Field("Description")
    private String description;

    @Field("Answer")
    private String answer;
}
