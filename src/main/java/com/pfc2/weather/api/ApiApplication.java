package com.pfc2.weather.api;

import com.pfc2.weather.api.entities.enums.Role;
import com.pfc2.weather.api.services.AuthenticationService;
import com.pfc2.weather.api.vos.AuthenticationResponseVo;
import com.pfc2.weather.api.vos.RegisterRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ApiApplication implements CommandLineRunner {

	private final AuthenticationService authenticationService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		authenticationService.register(RegisterRequestVo.builder()
				.firstname("Joel")
				.lastname("Chamorro")
				.email("joel@hotmail.com")
				.password("password")
				.role(Role.ADMIN)
				.build());
	}
}
