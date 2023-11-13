package com.pfc2.weather.api.controllers;

import com.pfc2.weather.api.services.UserService;
import com.pfc2.weather.api.vos.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for list users saved in database.
 * @author jchamorro
 * */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserVo>> findAll(){
        log.info("*** UserController.findAll");
        try {
            return ResponseEntity.ok(userService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
