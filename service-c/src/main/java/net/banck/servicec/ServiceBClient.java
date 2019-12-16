package net.banck.servicec;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

import static java.lang.String.format;

@FeignClient(value = "service-b", configuration = FeignConfigurationJwt.class)
public interface ServiceBClient {

    @SuppressWarnings("rawtypes")
    @GetMapping("/weather/{city}")
    @CircuitBreaker(name = "service-b/weather", fallbackMethod = "fallbackWeather")
    Map weather(@PathVariable String city);

    @SuppressWarnings("rawtypes")
    default Map fallbackWeather(String city, Throwable t) {
        return Map.of("Default Weather", format("Weather data for '%s' currently not available.", city));
    }

}
