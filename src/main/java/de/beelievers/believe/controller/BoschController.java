package de.beelievers.believe.controller;

import de.beelievers.believe.dto.AirQualityResponse;
import de.beelievers.believe.service.BoschService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
public class BoschController {

    private BoschService service;

    @Autowired
    public BoschController(BoschService service) {
        setService(service);
    }

    @RequestMapping(value = "/airQuality", method = RequestMethod.GET, produces = "application/json")
    public AirQualityResponse airQuality(@RequestParam(value="longitude") long longitude, @RequestParam
            (value="latitude") long latitude, @RequestParam(value="radius") int radius) throws ExecutionException,
            InterruptedException {
        return new AirQualityResponse(getService().requestAirQualityForLast24H(longitude, latitude, radius));
    }

}
