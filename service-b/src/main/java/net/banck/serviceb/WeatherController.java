package net.banck.serviceb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class WeatherController {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/{city}")
    public ResponseEntity<Weather> getWeather(@PathVariable String city) {
        LOG.info("Getting weather for City {}", city);
        Optional<Weather> weather = weatherService.getWeather(city);
        return weather.isPresent()
                ? ResponseEntity.ok(weather.get())
                : ResponseEntity.notFound().build();
    }

}
