package admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"admin", "com.jiao.order.parse", "com.jiao.client"})

public class AdminSpringBootApplication {

    public static void main(String[] args) {

        SpringApplication.run(AdminSpringBootApplication.class);
    }
}
