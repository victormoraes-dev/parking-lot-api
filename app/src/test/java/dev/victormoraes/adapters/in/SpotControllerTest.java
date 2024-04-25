package dev.victormoraes.adapters.in;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
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
                        .content("{\"floor\": \"1\",\"position\":\"A10\",\"isFree\":true,\"vehicleType\":\"CAR\"}")
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", matchesPattern(".*\\/spots\\/\\d+")));
    }

    @Test
    @Sql(scripts = "/insert-test-spot.sql") // SQL script to insert a test spot
    public void testUpdateSpotEndpoint() throws Exception {
        long spotId = 1L;
        String requestBody = "{\"isFree\":false, \"position\":\"A11\",\"vehicleType\":\"CAR\"}";

        mockMvc.perform(MockMvcRequestBuilders.patch("/spots/" + spotId)
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.free").value(false))
                .andExpect(jsonPath("$.data.position").value("A11"))
                .andExpect(jsonPath("$.data.vehicleType").value("CAR"));

    }

    @Test
    @Sql(scripts = "/insert-test-spot.sql")
    public void testDeleteSpotEndpoint() throws Exception {
        long spotId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/spots/" + spotId))
                .andExpect(status().isNoContent());
    }

    @Test
    @Sql(scripts = "/insert-test-spot.sql")
    public void testGetSpotEndpoint() throws Exception {
        long spotId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/spots/" + spotId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.spotId").value(spotId))
                .andExpect(jsonPath("$.data.floor").exists())
                .andExpect(jsonPath("$.data.position").exists())
                .andExpect(jsonPath("$.data.free").exists())
                .andExpect(jsonPath("$.data.vehicleType").exists());
    }
}