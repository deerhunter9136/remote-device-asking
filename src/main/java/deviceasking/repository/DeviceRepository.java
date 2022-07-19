package deviceasking.repository;

import deviceasking.model.AbstractDevice;
import deviceasking.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Object of this class stores device entities and performs DAO functions
 */
@Component
public class DeviceRepository {

    public static class NotFoundDevice extends AbstractDevice {
        public static final String NOT_FOUND_MESSAGE = "Device with current id is not presented";

        public NotFoundDevice(String uuid) {
            super(NOT_FOUND_MESSAGE, uuid);
        }

    }

    private final Map<String, Device> deviceMap = new ConcurrentHashMap<>();

    @Autowired
    public void addAll(List<Device> devices) {
        devices.forEach(device -> deviceMap.put(device.getId(), device));
    }

    public void add(Device device) {
        deviceMap.put(device.getId(), device);
    }

    public Collection<Device> getAll() {
        return deviceMap.values();
    }

    public List<String> getIds() {
        return deviceMap.values().stream().map(Device::getId).collect(Collectors.toList());
    }

    public Device getById(String id) {
        return deviceMap.getOrDefault(id, new NotFoundDevice(id));
    }
}
