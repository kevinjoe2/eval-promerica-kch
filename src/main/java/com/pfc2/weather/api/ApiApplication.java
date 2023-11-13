package com.pfc2.weather.api;

import com.pfc2.weather.api.exceptions.ApiException;
import com.pfc2.weather.api.utils.enums.Role;
import com.pfc2.weather.api.services.AuthenticationService;
import com.pfc2.weather.api.vos.AuthenticationRequestVo;
import com.pfc2.weather.api.vos.AuthenticationResponseVo;
import com.pfc2.weather.api.vos.RegisterRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ApiApplication implements CommandLineRunner {

	private final AuthenticationService authenticationService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			AuthenticationResponseVo resp = authenticationService.authenticate(AuthenticationRequestVo.builder()
					.email("joel@hotmail.com").password("password")
					.build());
			if (Objects.isNull(resp)) {
				authenticationService.register(RegisterRequestVo.builder()
						.firstname("Joel")
						.lastname("Chamorro")
						.email("joel@hotmail.com")
						.password("password")
						.role(Role.ADMIN)
						.build());
			}
		} catch (Exception ex) {
			if (ex.getCause() instanceof ApiException apiException
					&& apiException.getHttpStatus().equals(HttpStatus.NOT_FOUND)
			) {
				authenticationService.register(RegisterRequestVo.builder()
						.firstname("Joel")
						.lastname("Chamorro")
						.email("joel@hotmail.com")
						.password("password")
						.role(Role.ADMIN)
						.build());
			} else {
				log.error("*** CommandLineRunner usuario ya existe.", ex);
			}
		}
	}
}
