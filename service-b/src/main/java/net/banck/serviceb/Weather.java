package net.banck.serviceb;

import lombok.Data;

@Data
public class Weather {

    private String locationName;
    private Temperature temperature;
    private Long humidity;
    private Double windSpeed;
    private Long windDegree;
    private Long clouds;

    @Data
    static class Temperature {
        private Double value;
        private String unit;

        public static Temperature fromKelvin(Double kelvin) {
            Temperature t = new Temperature();
            t.setValue(kelvin - 273.15);
            t.setUnit("Celsius");
            return t;
        }
    }

    public static Weather from(WeatherApiData apiData) {
        Weather weather = new Weather();
        weather.setLocationName(apiData.getName());
        weather.setTemperature(Temperature.fromKelvin(apiData.getMain().getTemp()));
        weather.setHumidity(apiData.getMain().getHumidity());
        weather.setWindSpeed(apiData.getWind().getSpeed());
        weather.setWindDegree(apiData.getWind().getDeg());
        weather.setClouds(apiData.getClouds().getAll());
        return weather;
    }

    private static Long toCelsius(Double kelvin) {
        return Math.round(kelvin - 273.15);
    }

}
