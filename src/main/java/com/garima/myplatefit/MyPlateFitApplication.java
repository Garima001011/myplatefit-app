package com.garima.myplatefit;

import com.garima.myplatefit.model.TestEntity;
import com.garima.myplatefit.repository.TestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyPlateFitApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyPlateFitApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(TestRepository repo) {
        return args -> {
            TestEntity t = new TestEntity();
            t.setValue("Connected!");
            repo.save(t);
        };
    }
}
