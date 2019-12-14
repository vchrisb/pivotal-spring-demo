package net.banck.servicec;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(value = "service-b", configuration = FeignConfigurationJwt.class)
@CircuitBreaker(name = "service-b")
public interface ServiceBClient {

    @GetMapping("/weather/{city}")
    Map weather(@PathVariable String city);

}
