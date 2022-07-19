package deviceasking.config;

import deviceasking.model.Device;
import deviceasking.model.Scanner;
import deviceasking.model.Sensor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static deviceasking.util.Util.convert2Bytes;
/**
 * Configuration class
 */
@Configuration
public class Config {

    @Value("${arg1}")
    String arg1;

    @Value("${arg2}")
    String arg2;

    @Bean
    List<Device> devices() {
        List<Device> devices = new ArrayList<>();
        devices.add(new Scanner("scanner1", convert2Bytes(arg1)));
        devices.add(new Scanner("scanner2", convert2Bytes(arg2)));
        devices.add(new Sensor("sensor1"));
        devices.add(new Sensor("sensor2"));
        return devices;
    }
}
