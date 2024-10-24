package fr.afpa.orm.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import io.jsonwebtoken.lang.Arrays;

import java.util.List;
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // public SecurityConfiguration(
    //         JwtAuthenticationFilter jwtAuthenticationFilter,
    //         AuthenticationProvider authenticationProvider
    // ) {
    //     this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    //     this.authenticationProvider = authenticationProvider;
    // }
    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    //     return authenticationConfiguration.getAuthenticationManager();
    // }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return  http
       .cors(Customizer.withDefaults())
            .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
            .requestMatchers(HttpMethod.POST,"/", "/auth/**").permitAll()
            // .requestMatchers(HttpMethod.GET, "/users/me").permitAll()
            // .requestMatchers(HttpMethod.GET, "/api/clients/**").permitAll()
            // .requestMatchers(HttpMethod.GET, "api/accounts/**").hasAuthority(USER)
            .requestMatchers("/", "/error", "/csrf", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
          
            // .requestMatchers("/users", "/users/**").hasAuthority(USER)
                // .requestMatchers("/users/**").permitAll() 
                // .requestMatchers("/api/clients/**").permitAll()
                //    .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                // .requestMatchers("/accounts/**").hasRole("USER") 
                .anyRequest().authenticated())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
             
                .csrf(AbstractHttpConfigurer::disable)
                
                .build();
        
        

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
       
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); 
        configuration.setAllowedHeaders(List.of("Content-Type", "Authorization", "Accept"));
        configuration.setAllowedMethods(List.of("HEAD", "OPTIONS","GET", "POST", "PUT", "DELETE")); 
              configuration.setExposedHeaders(List.of("Access_token", "refresh_token"));
              configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); 
        return source;
    }
    

}