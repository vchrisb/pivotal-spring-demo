package net.banck.servicea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ConfigComponent configComponent;

    @GetMapping("/somevalue")
    public String getSomeValue() {
        return configComponent.getSomeValue();
    }

    @GetMapping("/somesecurevalue")
    public String getSomeSecureValue() {
        return configComponent.getSomeSecureValue();
    }

}
