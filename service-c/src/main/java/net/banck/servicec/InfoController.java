package net.banck.servicec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
public class InfoController {

    private final ServiceAClient serviceAClient;
    private final ServiceBClient serviceBClient;

    public InfoController(ServiceAClient serviceAClient, ServiceBClient serviceBClient) {
        this.serviceAClient = serviceAClient;
        this.serviceBClient = serviceBClient;
    }

    @GetMapping("/info")
    public Collection<City> info() {
        Collection<City> cities = serviceAClient.cities().getContent();
        cities.forEach(c -> c.setWeather(serviceBClient.weather(c.getName())));

        return cities;
    }

    @GetMapping("/cities")
    public Collection<City> cities() {
        return serviceAClient.cities().getContent();
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/weather/{city}")
    public Map weather(@PathVariable String city) {
        return serviceBClient.weather(city);
    }

}
