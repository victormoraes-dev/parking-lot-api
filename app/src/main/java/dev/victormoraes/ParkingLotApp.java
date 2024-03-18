package dev.victormoraes;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ParkingLotApp {

    public static void main(String[] args) {
        SpringApplication.run(ParkingLotApp.class, args);
    }
}
