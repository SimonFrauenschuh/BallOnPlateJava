package diploma.thesis.repositories;

import diploma.thesis.model.Result;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface ResultRepository {
    Result findById(int id);

    @Query("SELECT * FROM result ORDER BY id DESC LIMIT 1")
    Result findFirstOrderedById();

    Result save(Result result);
}
