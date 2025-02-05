package com.Ntra.ProGig.Configration;

import com.Ntra.ProGig.Entity.Role;
import com.Ntra.ProGig.Filter.JwtAuthFilter;
import com.Ntra.ProGig.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private final UserServiceImpl userService;
    @Autowired
    private final JwtAuthFilter jwtAuthFilter;
//    @Bean
//    public CorsFilter corsilter () {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.setAllowedOriginPatterns(Collections.singletonList("*"));
//        config.addAllowedHeader ("*");
//        config.addAllowedMethod("*");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source. registerCorsConfiguration( "/*", config);
//        return new CorsFilter (source);
////    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    private static final String[] Public_URL= {
            "/Login/**", "/v3/api-docs", "/v2/api-docs","/swagger-resources/**",

            "/swagger-ui/**",
            "/webjars/**","/api-docs/**","/profile"
    };
//    private static final String[] Private_URL={
//            "/register/**","/users/{id}/**","/update/**","/delet/{id}/**",
//            "/Skills/**","/jobs/**","/clients/**","/Transaction/**","/proposals/**",
//            "/freelancerCount","/contract/**","/Invoice/**","/millstone/**"
//            ,"/clientCount","/Users/**","/freelancer/**","/user_api/**"
//    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        Req->Req.requestMatchers(Public_URL)
                                .permitAll()
                                .requestMatchers("/update/**").hasAuthority("SUPER_ADMIN")
                                /*.requestMatchers(Private_URL)*/.anyRequest()
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
