package com.ml.serviceapi.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.serviceapi.ServiceApiApplication;
import com.ml.serviceapi.controllers.requests.HelloWorldRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ContextConfiguration(classes = {ServiceApiApplication.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ServiceApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/serviceapi/helloWorld"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello, World!")));
    }

    @Test
    public void shouldReturnHelloWorld2() throws Exception {
        mockMvc.perform(get("/serviceapi/greet"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello, World!")));
    }

    @Test
    public void shouldReturnHelloMeir() throws Exception {
        mockMvc.perform(get("/serviceapi/greet?name=Mike"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello, Mike!")));
    }

    @Test
    public void shouldReturnGoodMorningDavid() throws Exception {

        HelloWorldRequest request = new HelloWorldRequest();
        request.setGreet("Good morning");
        request.setName("David");
        String requestJson = asJsonString(request);


        mockMvc.perform(post("/serviceapi/helloWorld")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.method").value("helloWorld"))
                .andExpect(jsonPath("$.type").value("POST"))
                .andExpect(jsonPath("$.msg").value("Good morning, David!"));
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
