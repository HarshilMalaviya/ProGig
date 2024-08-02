package com.Ntra.ProGig.Configration;

import com.Ntra.ProGig.Filter.JwtAuthFilter;
import com.Ntra.ProGig.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@RequiredArgsConstructor
public class SecutiryConfig {
    private final UserServiceImpl userService;
    private final JwtAuthFilter jwtAuthFilter;
    private static final String[] Public_URL= {
            "/login/**", "/v3/api-docs", "/v2/api-docs","/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**","/api-docs/**"
    };
    private static final String[] Private_URL={
            "/register/**","/Users/**","/users/{id}/**","/update/**","/delet/{id}/**",
            "/Skills/**","/jobs/**","/freelancer/**","/clients/**","/Transaction/**","/proposals/**"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        Req->Req.requestMatchers(Public_URL)
                                .permitAll()
                                .requestMatchers(Private_URL)
                                .authenticated()

                ).userDetailsService(userService)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
