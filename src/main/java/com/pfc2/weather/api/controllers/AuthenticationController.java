package com.pfc2.weather.api.controllers;

import com.pfc2.weather.api.services.AuthenticationService;
import com.pfc2.weather.api.vos.AuthenticationRequestVo;
import com.pfc2.weather.api.vos.AuthenticationResponseVo;
import com.pfc2.weather.api.vos.RegisterRequestVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseVo> register(
            @RequestBody RegisterRequestVo request
    ) {
        log.info("*** AuthenticationController.register {}", request);
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseVo> authenticate(
            @RequestBody AuthenticationRequestVo request
    ) {
        log.info("*** AuthenticationController.authenticate {}", request);
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        log.info("*** AuthenticationController.refreshToken {}", request);
        service.refreshToken(request, response);
    }


}
