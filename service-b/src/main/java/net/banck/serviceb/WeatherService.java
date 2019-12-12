package net.banck.serviceb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class WeatherService {

    @Value("${weather.api.endpoint}")
    private String apiEndpoint;

    @Value("${weather.api.apiKey}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public Optional<Weather> getWeather(String city) {
        String uri = UriComponentsBuilder
                .fromUriString(apiEndpoint)
                .queryParam("q", String.format("%s,de", city))
                .queryParam("APPID", apiKey)
                .toUriString();

        ResponseEntity<WeatherApiData> response = restTemplate.getForEntity(uri, WeatherApiData.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return Optional.of(Weather.from(response.getBody()));
        }
        return Optional.empty();
    }

}
