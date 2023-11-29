package diploma.thesis.model;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@MappedEntity
@Serdeable
public class Result {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    @NonNull
    private int result;

    @Setter
    @Getter
    @NonNull
    private int mode;

    @Setter
    @Getter
    @NonNull
    private int error;
}
