package net.banck.serviceb;

import lombok.Data;

@Data
public class WeatherApiData {

    private String name;
    private Main main;
    private Wind wind;
    private Clouds clouds;

    @Data
    static class Main {
        private Double temp;
        private Long humidity;
    }

    @Data
    static class Wind {
        private Double speed;
        private Long deg;
    }

    @Data
    static class Clouds {
        private Long all;
    }

}
