package net.banck.serviceb;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    @Disabled // add API key in WeatherService to run the test
    public void testMunich() {
        Optional<Weather> weather = weatherService.getWeather("Munich");
        assertThat(weather).isNotEmpty();
        assertThat(weather.get().getLocationName()).isEqualTo("Munich");
        assertThat(weather.get().getTemperature().getValue()).isGreaterThan(-99.99);
    }

}
