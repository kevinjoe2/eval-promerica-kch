package com.pfc2.weather.api.configs;

import com.pfc2.weather.api.utils.enums.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.pfc2.weather.api.utils.enums.Role.ADMIN;
import static com.pfc2.weather.api.utils.enums.Role.USER;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/authenticate",
            "/api/v1/auth/refresh-token",
            "/sys/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"};
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers("/api/v1/weather").hasAnyRole(ADMIN.name(), USER.name())
                        .requestMatchers("/api/v1/weather/**").hasAnyRole(ADMIN.name(), USER.name())
                        .requestMatchers("/api/v1/auth/register").hasAnyRole(ADMIN.name(), USER.name())
                        .requestMatchers(POST, "/api/v1/auth/register").hasAnyRole(Permission.ADMIN_CREATE.name())
                        .requestMatchers(GET, "/api/v1/weather/history").hasAnyAuthority(Permission.ADMIN_READ.name(),  Permission.USER_READ.name())
                        .requestMatchers(POST, "/api/v1/weather").hasAnyAuthority(Permission.ADMIN_CREATE.name(), Permission.USER_CREATE.name())
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(abc -> abc
                .authenticationEntryPoint(new AuthenticationEntryPointConfig())
                .accessDeniedPage("/errors/access-denied")
        );
        return http.build();
    }
}