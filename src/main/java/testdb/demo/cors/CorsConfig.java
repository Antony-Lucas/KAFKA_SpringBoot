package testdb.demo.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class CorsConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/**").permitAll()
                                .anyRequest().authenticated()
                );

        http.headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()));


        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("http://localhost:333", "http://localhost:4200"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "OPTIONS", "PUT", "DELETE"));
            configuration.setAllowedHeaders(Arrays.asList("Origin",
                    "Accept",
                    "Content-Type",
                    "Authorization",
                    "X-Requested-With",
                    "Cache-Control",
                    "If-Modified-Since"));
            configuration.setAllowCredentials(true);
            return configuration;
        }));

        return http.build();
    }
}
