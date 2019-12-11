package net.banck.servicec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {


    private final ServiceAClient serviceAClient;

    public InfoController(ServiceAClient serviceAClient) {
        this.serviceAClient = serviceAClient;
    }

    @GetMapping("/info")
    public String info() {

        return serviceAClient.cities();
    }
}
