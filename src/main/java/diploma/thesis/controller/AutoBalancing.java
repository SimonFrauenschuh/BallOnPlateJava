package diploma.thesis.controller;

import diploma.thesis.model.Position;
import diploma.thesis.model.Result;
import diploma.thesis.repositories.PositionRepository;
import diploma.thesis.repositories.ResultRepository;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.serde.ObjectMapper;
import jakarta.inject.Inject;
import org.json.JSONObject;

import java.io.IOException;

@Controller("/")
public class AutoBalancing {

    @Inject
    ResultRepository resultRepository;

    @Inject
    PositionRepository positionRepository;

    @Inject
    ObjectMapper objectMapper;

    @Get
    public String getCurrentPosition() throws IOException {
        return objectMapper.writeValueAsString(positionRepository.findFirstOrderedById());
    }

    @Post
    public void registerNewEstimatedPosition(@Body String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        // If the site is opened, ensure that the mode is set to auto-balancing
        if (jsonObject.has("setAutoBalancingMode")) {
            Result result = new Result();
            result.setMode(0);
            result.setResult(100);

            resultRepository.save(result);
        } else {
            Position position = new Position();
            position.setXest(jsonObject.getInt("xEst"));
            position.setYest(jsonObject.getInt("yEst"));
            position.setXreal(0);
            position.setYreal(0);

            positionRepository.save(position);
        }
    }
}
