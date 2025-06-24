package backend.interview.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "backend.interview")
public class WidgetApplication {

    public static void main(String[] args) {
        SpringApplication.run(WidgetApplication.class, args);
    }

}
