package autodealer.com.logic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "autodealer.com.logic")
public class AutoDealerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoDealerApplication.class, args);
    }
}
