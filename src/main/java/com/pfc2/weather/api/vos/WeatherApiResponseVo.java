package com.pfc2.weather.api.vos;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class WeatherApiResponseVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251208L;

    @Data
    public static class Coord implements Serializable {

        @Serial
        private static final long serialVersionUID = 1905122041950251208L;
        private double lon;
        private double lat;
    }

    @Data
    public static class Weather implements Serializable {

        @Serial
        private static final long serialVersionUID = 1905122041950251208L;
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class Main implements Serializable {

        @Serial
        private static final long serialVersionUID = 1905122041950251208L;
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;
        private int sea_level;
        private int grnd_level;
    }

    @Data
    public static class Wind implements Serializable {

        @Serial
        private static final long serialVersionUID = 1905122041950251208L;
        private double speed;
        private int deg;
        private double gust;
    }

    @Data
    public static class Clouds implements Serializable {

        @Serial
        private static final long serialVersionUID = 1905122041950251208L;
        private int all;
    }

    @Data
    public static class Sys implements Serializable {

        @Serial
        private static final long serialVersionUID = 1905122041950251208L;
        private String country;
        private long sunrise;
        private long sunset;
    }

    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
}