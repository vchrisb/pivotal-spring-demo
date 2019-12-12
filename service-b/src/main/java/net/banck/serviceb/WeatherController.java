package net.banck.serviceb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/{city}")
    public ResponseEntity<Weather> getWeather(@PathVariable String city) {
        Optional<Weather> weather = weatherService.getWeather(city);
        return weather.isPresent()
                ? ResponseEntity.ok(weather.get())
                : ResponseEntity.notFound().build();
    }

}
