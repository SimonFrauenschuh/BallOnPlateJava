package diploma.thesis.repositories;

import diploma.thesis.model.Position;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface PositionRepository {

    Position findById(int id);

    @Query("SELECT * FROM position ORDER BY id DESC LIMIT 1")
    Position findFirstOrderedById();

    Position save(Position position);
}
