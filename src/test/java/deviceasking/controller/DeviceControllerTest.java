package deviceasking.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import deviceasking.model.Device;
import deviceasking.model.Scanner;
import deviceasking.repository.DeviceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static deviceasking.util.Util.convertFromBytes;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class DeviceControllerTest extends AbstractControllerTest {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAll() throws Exception {
        ResultActions actions = perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
        System.out.println(actions);
        String contentAsString = getContentAsString(actions);
        List objects = objectMapper.readValue(contentAsString, List.class);
        Assertions.assertEquals(objects.size(), deviceRepository.getAll().size());
    }

    @Test
    void getById() throws Exception {
        String someId = deviceRepository.getIds().get(0);
        ResultActions actions = perform(MockMvcRequestBuilders.get("/" + someId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
        String contentAsString = getContentAsString(actions);
        Map map = objectMapper.readValue(contentAsString, Map.class);
        String deviceType = DeviceRepository.NotFoundDevice.class.getSimpleName();
        Assertions.assertNotEquals(deviceType, map.get("type"));
    }

    @Test
    void getByIdNotExistent() throws Exception {
        ResultActions actions = perform(MockMvcRequestBuilders.get("/" + UUID.randomUUID()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
        String contentAsString = getContentAsString(actions);
        Map map = objectMapper.readValue(contentAsString, Map.class);
        String deviceType = DeviceRepository.NotFoundDevice.class.getSimpleName();
        Assertions.assertEquals(deviceType, map.get("type"));
    }

    @Test
    void ask() throws Exception {
        ResultActions actions = perform(MockMvcRequestBuilders.get("/id/ask"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
        String contentAsString = getContentAsString(actions);
        Map map = objectMapper.readValue(contentAsString, Map.class);
        Assertions.assertNotNull(map.get("responce"));
    }

    @Test
    void askWithTrueResponce() throws Exception {
        byte[] bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Device device = new Scanner("newScanner", bytes);
        deviceRepository.add(device);
        ResultActions actions = perform(MockMvcRequestBuilders.get(String.format("/%s/ask", device.getId()))
                .param("param", convertFromBytes(bytes)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
        String contentAsString = getContentAsString(actions);
        Map map = objectMapper.readValue(contentAsString, Map.class);
        Assertions.assertTrue((Boolean) map.get("responce"));

    }
}