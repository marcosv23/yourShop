package config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

   @Bean
   Flyway flyway(){
        return Flyway.configure().load();
    }
}
