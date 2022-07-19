package deviceasking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeviceServerApplication {
    /**
     * Starts web server application
     */
    public static void main(String[] args) {
        SpringApplication.run(DeviceServerApplication.class, args);
    }
}
