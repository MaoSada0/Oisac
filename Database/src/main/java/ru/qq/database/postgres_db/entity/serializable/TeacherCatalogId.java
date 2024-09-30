package ru.qq.database.postgres_db.entity.serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class TeacherCatalogId implements Serializable {
    private Long teacherId;
    private Long catalogId;
}
