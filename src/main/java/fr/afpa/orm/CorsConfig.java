package fr.afpa.orm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173") 
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization", "Access-Control-Allow-Origin")
                        .maxAge(3600);
            }
        };
    }
    
<<<<<<< HEAD
}
=======
}
>>>>>>> 0f9a292d2045b83a746bf1b2bda385d1385bf3c8
