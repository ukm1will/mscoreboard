package application;

import models.MyObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        SpringApplication.run(Application.class, args);
    }
}

