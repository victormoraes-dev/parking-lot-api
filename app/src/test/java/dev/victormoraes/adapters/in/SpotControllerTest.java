package dev.victormoraes.adapters.in;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpotControllerTest extends TestContainersBaseConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateSpotEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/spots")
                        .content("{\"floor\": 1,\"position\":\"A10\",\"isFree\":true,\"vehicleType\":\"CAR\"}")
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", matchesPattern(".*\\/spots\\/\\d+")));
    }
}