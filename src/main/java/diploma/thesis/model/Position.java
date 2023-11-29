package diploma.thesis.model;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable
@MappedEntity
public class Position {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    @Setter
    @Getter
    private int id;

    @NonNull
    @Setter
    @Getter
    private int xest;

    @NonNull
    @Setter
    @Getter
    private int yest;

    @NonNull
    @Setter
    @Getter
    private int xreal;

    @NonNull
    @Setter
    @Getter
    private int yreal;
}
