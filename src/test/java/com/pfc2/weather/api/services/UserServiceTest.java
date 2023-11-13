package com.pfc2.weather.api.services;

import com.pfc2.weather.api.entities.UserEntity;
import com.pfc2.weather.api.repositories.UserRepository;
import com.pfc2.weather.api.utils.enums.Role;
import com.pfc2.weather.api.vos.UserVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

/**
 * Test for UserService
 * @author jchamorro
 * */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private UserService service;

    @Test
    void findAll() {
        List<UserEntity> list1 = new ArrayList<>();
        list1.add(UserEntity.builder()
                .email("joel@hotmail.com")
                .password("password")
                .role(Role.ADMIN)
                .build());
        Mockito.when(userRepository.findAll()).thenReturn(list1);
        List<UserVo> result = service.findAll();
        Assertions.assertFalse(result.isEmpty());
    }
}