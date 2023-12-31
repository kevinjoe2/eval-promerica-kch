package com.pfc2.weather.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity for save weather history.
 * @author jchamorro
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weathers_history")
public class WeatherHistoryEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251208L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(precision = 6, scale = 4)
    private BigDecimal lat;
    @Column(precision = 7, scale = 4)
    private BigDecimal lon;
    private String weather;
    private BigDecimal tempMin;
    private BigDecimal tempMax;
    private BigDecimal humidity;
    private LocalDateTime created;

}
