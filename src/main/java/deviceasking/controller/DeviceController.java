package deviceasking.controller;

import deviceasking.model.Device;
import deviceasking.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static deviceasking.util.Util.convert2Bytes;
/**
 * Object of this class handles requests
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceController {
    @Autowired
    DeviceRepository repository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Collection<Device> getAll() {
        return repository.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Device getById(@PathVariable String id) {
        return repository.getById(id);
    }

    @GetMapping("/{id}/ask")
    @ResponseStatus(HttpStatus.OK)
    Device.WithResponce ask(@PathVariable String id, @Nullable @RequestParam String param) {
        Device device = repository.getById(id);
        return new Device.WithResponce(device, device.ask(convert2Bytes(param)));
    }
}
