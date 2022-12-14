package canard.intern.post.following.backend.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Interet de faire cette config:
// 1: par recréer à chaque fois, permet d'utiliser le autowired qui se lance au démarrage
// 2: pour avoir une config custom sans la réécrire à chaque fois

@Configuration
public class ModelMapperConfig {
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    };
}
