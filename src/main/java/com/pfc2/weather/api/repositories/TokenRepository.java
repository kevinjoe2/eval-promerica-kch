package com.pfc2.weather.api.repositories;

import com.pfc2.weather.api.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for token.
 * @author jchamorro
 * */
@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {

    @Query(value = """
      select t from TokenEntity t inner join UserEntity u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<TokenEntity> findAllValidTokenByUser(String id);

    Optional<TokenEntity> findByToken(String token);
}
