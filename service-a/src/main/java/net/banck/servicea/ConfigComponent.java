package net.banck.servicea;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@Data
public class ConfigComponent {

    @Value("${some.value:defaultValue}")
    private String someValue;

}
