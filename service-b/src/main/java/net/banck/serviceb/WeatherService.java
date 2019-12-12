package net.banck.serviceb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static java.lang.String.format;

@Service
public class WeatherService {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherController.class);

    private static final String QUERY_PARAM_NAME = "q";
    private static final String API_KEY_NAME = "APPID";
    private static final String GERMANY = "de";

    @Value("${weather.api.endpoint}")
    private String apiEndpoint;

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public Optional<Weather> getWeather(String city) {
        String uri = UriComponentsBuilder
                .fromUriString(apiEndpoint)
                .queryParam(QUERY_PARAM_NAME, format("%s,%s", city, GERMANY))
                .queryParam(API_KEY_NAME, apiKey)
                .toUriString();

        ResponseEntity<WeatherApiData> response = restTemplate.getForEntity(uri, WeatherApiData.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            Weather weather = Weather.from(response.getBody());
            LOG.info("Found weather for '{}': {}", city, weather);
            return Optional.of(weather);
        }
        LOG.info("No weather info for '{}'", city);
        return Optional.empty();
    }

}
