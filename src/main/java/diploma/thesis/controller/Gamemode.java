package diploma.thesis.controller;

import diploma.thesis.model.Position;
import diploma.thesis.model.Result;
import diploma.thesis.repositories.PositionRepository;
import diploma.thesis.repositories.ResultRepository;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.serde.ObjectMapper;
import jakarta.inject.Inject;
import org.json.JSONObject;

import java.io.IOException;

@Controller("/gamemode")
public class Gamemode {

    @Inject
    PositionRepository positionRepository;

    @Inject
    ResultRepository resultRepository;

    @Inject
    ObjectMapper objectMapper;

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public String getCurrentResult() throws IOException {
        return objectMapper.writeValueAsString(resultRepository.findFirstOrderedById());
    }

    @Post
    void registerSmartphoneAngle(@Body String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        // If new gyroscope-values are sent --> Format {"xEst":12, "yEst":13}
        if (jsonObject.length() > 1) {
            Position gyroscopeAngle = new Position();
            gyroscopeAngle.setXest(jsonObject.getInt("xEst"));
            gyroscopeAngle.setYest(jsonObject.getInt("yEst"));
            gyroscopeAngle.setXreal(303);
            gyroscopeAngle.setYreal(303);

            positionRepository.save(gyroscopeAngle);
        }
        // If gamemode 1 (standard balancing) is selected
        else if (jsonObject.getInt("mode") == 1) {
            Result chosenMode = new Result();
            chosenMode.setMode(1);
            chosenMode.setResult(100);
            chosenMode.setError(0);
            resultRepository.save(chosenMode);
        }// If gamemode 2 (labyrinth) is selected
        else if (jsonObject.getInt("mode") == 2) {
            Result chosenMode = new Result();
            chosenMode.setMode(2);
            chosenMode.setResult(100);
            chosenMode.setError(0);
            resultRepository.save(chosenMode);
        }
    }
}
