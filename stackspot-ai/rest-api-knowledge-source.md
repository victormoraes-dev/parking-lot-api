# Spring Boot Application Structure Knowledge Source

This document serves as a Knowledge Source (KS) for creating APIs within a Spring Boot application following a specific architecture pattern. The application structure is defined as controller -> use case -> ports (implemented by adapter classes) -> repositories -> entities.

## Application Components

### 1. Controller

Controllers handle incoming HTTP requests and respond to the user or external system. They call upon use cases to perform specific business logic.

#### Example: SpotController

```java
@RestController
@RequestMapping("/spots")
public class SpotController {

    private final CreatingSpotUseCase useCase;
    private final UpdatingSpotUseCase updatingSpotUseCase;
    private final HttpServletRequest request;

    public SpotController(CreatingSpotUseCase useCase, UpdatingSpotUseCase updatingSpotUseCase, HttpServletRequest request) {
        this.useCase = useCase;
        this.updatingSpotUseCase = updatingSpotUseCase;
        this.request = request;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<SpotResponseDTO>> createSpot(@RequestBody SpotRequestDTO spotRequestDTO)
            throws URISyntaxException {
        var result = useCase.createSpot(SpotMapper.toDomain(spotRequestDTO));
        if (!result.isSuccess()) {
            return ResponseEntity.unprocessableEntity().body(ResponseWrapper.error(result.getErrorMessage()));
        }
        SpotResponseDTO spotResponseDTO = SpotMapper.toResponseDTO(result.getResult());
        String currentUrl = request.getRequestURL().toString();
        return ResponseEntity.created(new URI(currentUrl + "/" + spotResponseDTO.getSpotId()))
                .body(ResponseWrapper.success(spotResponseDTO));
    }

    @PatchMapping("/{spotId}")
    public ResponseEntity<ResponseWrapper<SpotResponseDTO>> updateSpot(@PathVariable Long spotId, @RequestBody SpotRequestDTO spotRequestDTO) {
        var domainSpot = SpotMapper.toDomain(spotRequestDTO);
        var result = updatingSpotUseCase.updateSpot(spotId, domainSpot);
        if (!result.isSuccess()) {
            return ResponseEntity.unprocessableEntity().body(ResponseWrapper.error(result.getErrorMessage()));
        }
        SpotResponseDTO spotResponseDTO = SpotMapper.toResponseDTO(result.getResult());
        return ResponseEntity.ok(ResponseWrapper.success(spotResponseDTO));
    }
}
```

## 2. Use Case
Use cases encapsulate and execute specific business logic of the application.

### Example: CreatingSpotUseCase

```java
@Component
public class CreatingSpotUseCase {

    private final CreateSpotPort createSpotPort;

    public CreatingSpotUseCase(CreateSpotPort createSpotPort) {
        this.createSpotPort = createSpotPort;
    }

    public Result<Spot> createSpot(Spot spot) {
        var createdSpot = createSpotPort.createSpot(spot);
        var result = new Result<Spot>(true);
        result.setResult(createdSpot);
        return result;
    }
}
```
## 3. Ports

Ports define the interfaces for the primary capabilities of the domain. They are implemented by adapters.

### Example: CreateSpotPort

```java
public interface CreateSpotPort {
    Spot createSpot(Spot spot);
}
````

## 4. Adapters
Adapters implement the ports and adapt the domain logic to external systems or frameworks, such as databases.

### Example: CreateSpotAdapter

```java
@Component
public class CreateSpotAdapter implements CreateSpotPort {

    private final SpotRepository spotRepository;

    public CreateSpotAdapter(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public Spot createSpot(Spot spot) {
        SpotEntity entity = SpotMapper.toEntity(spot);
        var savedEntity = spotRepository.save(entity);
        return SpotMapper.toDomain(savedEntity);
    }
}
```
## 5. Repositories and Entities
Repositories handle data persistence logic. Entities represent the domain objects in the database.

### Example: SpotEntity
```java
package dev.victormoraes.adapters.out.persistence.entities;

import dev.victormoraes.domain.vehicle.VehicleType;
import jakarta.persistence.*;

@Entity
@Table(name = "spots")
public class SpotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotId;
    @Column(nullable = false)
    private int floor;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private boolean isFree;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public SpotEntity(int floor, String position, boolean isFree, VehicleType vehicleType) {
        this.floor = floor;
        this.position = position;
        this.isFree = isFree;
        this.vehicleType = vehicleType;
    }

    public SpotEntity() {}

    // Getters and Setters
}
```

## 6. Integration Test
Integration tests class using junit 5, test containers and mock MVC

```java
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
```