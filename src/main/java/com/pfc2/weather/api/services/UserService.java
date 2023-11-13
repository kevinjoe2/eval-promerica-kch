package com.pfc2.weather.api.services;

import com.pfc2.weather.api.entities.UserEntity;
import com.pfc2.weather.api.repositories.UserRepository;
import com.pfc2.weather.api.vos.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for users.
 * @author jchamorro
 * */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserVo> findAll(){
        List<UserEntity> entities = userRepository.findAll();
        return entities.stream().map(this::toUserVo).toList();
    }

    private UserVo toUserVo(UserEntity entity) {
        return UserVo.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole())
                .build();
    }

}
