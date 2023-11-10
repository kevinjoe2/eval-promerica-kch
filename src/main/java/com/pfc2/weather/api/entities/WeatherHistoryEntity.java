package com.pfc2.weather.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "weathers_history")
@Getter
@Setter
public class WeatherHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigDecimal lat;
    private BigDecimal lon;
    private String weather;
    private BigDecimal tempMin;
    private BigDecimal tempMax;
    private BigDecimal humidity;
    private LocalDateTime created;

}
