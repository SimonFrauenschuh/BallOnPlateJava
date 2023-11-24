package diploma.thesis.model;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@MappedEntity
public class Position {
    @Id
    @GeneratedValue(GeneratedValue.Type.SEQUENCE)
    private int id;

    @NonNull
    private int xEst;

    @NonNull
    private int yEst;

    @NonNull
    private int xReal;

    @NonNull
    private int yReal;
}
