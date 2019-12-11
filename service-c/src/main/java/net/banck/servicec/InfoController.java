package net.banck.servicec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InfoController {


    private final ServiceAClient serviceAClient;
    private final ServiceBClient serviceBClient;

    public InfoController(ServiceAClient serviceAClient, ServiceBClient serviceBClient) {
        this.serviceAClient = serviceAClient;
        this.serviceBClient = serviceBClient;
    }

    @GetMapping("/info")
    public String info() {
        List<String> cityNames = new ArrayList<>();
        serviceAClient.cities().getContent().iterator().forEachRemaining(city -> cityNames.add(city.getName()));
        List<String> treeNames = new ArrayList<>();
        serviceBClient.trees().getContent().iterator().forEachRemaining(tree -> treeNames.add(tree.getName()));
        return cityNames.toString() + treeNames.toString();
    }
}
