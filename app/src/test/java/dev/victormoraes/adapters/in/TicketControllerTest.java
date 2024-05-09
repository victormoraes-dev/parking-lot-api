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
class TicketControllerTest extends TestContainersBaseConfigTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    @Sql(scripts = "/create-ticket-insert-available-spot.sql")
    public void givenAValidTicketBodyShouldCreateATicket() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/tickets")
                        .content("{\"vehicle\":{\"plate\":\"ABC123\",\"model\":\"Toyota Corolla\",\"color\":\"Red\",\"type\":\"CAR\"},\"vehicleType\":\"CAR\"}")
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.ticketId").isNumber())
                .andExpect(jsonPath("$.data.vehicle.plate").value("ABC123"))
                .andExpect(jsonPath("$.data.vehicle.model").value("Toyota Corolla"))
                .andExpect(jsonPath("$.data.vehicle.color").value("Red"))
                .andExpect(jsonPath("$.data.vehicle.type").value("CAR"))
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", matchesPattern(".*\\/tickets\\/\\d+")));
    }

    @Test
    public void givenAnInvalidVehiclePlateShouldReturnBadRequestHttpStatusCode() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/tickets")
                        .content("{\"vehicle\":{\"plate\":\"\",\"model\":\"Toyota Corolla\",\"color\":\"Red\",\"type\":\"CAR\"},\"vehicleType\":\"CAR\"}")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/update-spots-to-be-unavailable.sql")
    public void givenThereIsNoSpotAvailableShouldReturn422StatusCode() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/tickets")
                        .content("{\"vehicle\":{\"plate\":\"ABC123\",\"model\":\"Toyota Corolla\",\"color\":\"Red\",\"type\":\"CAR\"},\"vehicleType\":\"CAR\"}")
                        .contentType("application/json"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    @Sql(scripts = {"/insert-ticket-1000.sql"})
    public void givenATicketIdWhenFinalizeThenShouldUpdateTicketEndDate() throws Exception {

        long ticketId = 1000L;

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/tickets/" + ticketId + "/finalize")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.ticketId").value(ticketId))
                .andExpect(jsonPath("$.data.endTime").isNotEmpty());
    }
}