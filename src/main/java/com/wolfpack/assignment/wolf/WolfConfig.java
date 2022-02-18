package com.wolfpack.assignment.wolf;

import com.wolfpack.assignment.model.Wolf;
import com.wolfpack.assignment.repositories.WolfRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

// simple mock data
@Configuration
public class WolfConfig {
    @Bean
    CommandLineRunner commandLineRunner(WolfRepository repository) {
        return args -> {
            Wolf jack = new Wolf(
                    "Ivan",
                    "Male",
                    LocalDate.of(2000, 01, 5),
                    1.2f,
                    2.3f
            );
            Wolf hugh = new Wolf(
                    "Georg",
                    "Male",
                    LocalDate.of(1980, 01, 5),
                    2.3f,
                    6.5f
            );
            Wolf batman = new Wolf(
                    "Viki",
                    "Female",
                    LocalDate.of(2005, 03, 3),
                    1.3f,
                    10.5f
            );
            repository.saveAll(
                    List.of(jack, hugh, batman)
            );
        };
    }
}
