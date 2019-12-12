package net.banck.servicec;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@FeignClient(value = "service-b", configuration = FeignConfiguration.class)
@CircuitBreaker(name = "service-b", fallbackMethod = "fallback2")
public interface ServiceBClient {
    @RequestMapping("/trees")
    CollectionModel<Tree> trees();

    default CollectionModel<Tree> fallback2() {
        System.out.println("fallback");
        return new CollectionModel<Tree>(new ArrayList<>());
    }
}
