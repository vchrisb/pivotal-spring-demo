package net.banck.servicec;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.RequestMapping;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@FeignClient(value = "service-a", configuration = FeignConfigurationJwtTokenRelay.class)
@CircuitBreaker(name = "service-a")
public interface ServiceAClient {
    @RequestMapping("/cities")
    CollectionModel<City> cities();
}
