package net.banck.servicec;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@FeignClient(value = "service-b", configuration = FeignConfiguration.class)
public interface ServiceBClient {
    @RequestMapping("/trees")
    CollectionModel<Tree> trees();
}

