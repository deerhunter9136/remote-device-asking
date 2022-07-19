package deviceasking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.UnsupportedEncodingException;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    String getContentAsString(ResultActions actions) throws UnsupportedEncodingException {
        return actions.andReturn().getResponse().getContentAsString();
    }
}
