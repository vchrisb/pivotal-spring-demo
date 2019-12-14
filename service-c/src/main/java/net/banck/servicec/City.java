package net.banck.servicec;

import lombok.Data;

import java.util.Map;

@Data
public class City {

    private long id;
    private String name;
    private Long population;
    private Map weather;

}
