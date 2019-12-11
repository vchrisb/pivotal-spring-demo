package net.banck.servicec;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value = "service-a", configuration = FeignConfiguration.class)
public interface ServiceAClient {
    @RequestMapping("/cities")
    String cities();
}
