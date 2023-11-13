package com.pfc2.weather.api.repositories;

import com.pfc2.weather.api.entities.WeatherHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Repository for weather
 * @author jchamorro
 * */
@Repository
public interface WeatherRepository extends JpaRepository<WeatherHistoryEntity, String> {

    Optional<WeatherHistoryEntity> findByLatAndLon(BigDecimal lan, BigDecimal lon);
}
