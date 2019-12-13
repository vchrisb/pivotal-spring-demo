package net.banck.servicec;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@FeignClient(value = "service-b", configuration = FeignConfigurationJwtTokenRelay.class)
@CircuitBreaker(name = "service-b", fallbackMethod = "fallback2")
public interface ServiceBClient {
    @RequestMapping("/trees")
    CollectionModel<Tree> trees();

    default CollectionModel<Tree> fallback2() {
        System.out.println("fallback");
        return new CollectionModel<Tree>(new ArrayList<>());
    }
}
