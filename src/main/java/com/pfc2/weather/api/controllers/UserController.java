package com.pfc2.weather.api.controllers;

import com.pfc2.weather.api.services.UserService;
import com.pfc2.weather.api.vos.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserVo>> findAll(){
        try {
            return ResponseEntity.ok(userService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}