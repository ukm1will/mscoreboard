package application;

import models.MyObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        MyObject myObject = new MyObject(43, "Foobar");
        System.out.println(myObject.toString());
        SpringApplication.run(Application.class, args);
    }
}
