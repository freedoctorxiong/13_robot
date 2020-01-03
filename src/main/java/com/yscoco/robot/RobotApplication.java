package com.yscoco.robot;

import com.yscoco.robot.common.ConfigInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;

@SpringBootApplication
public class RobotApplication {

    public static void main(String[] args) throws IOException {
        ConfigInitializer.init();
        SpringApplication.run(RobotApplication.class, args);
    }

}
