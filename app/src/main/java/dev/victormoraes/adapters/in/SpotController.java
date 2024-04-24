package dev.victormoraes.adapters.in;

import dev.victormoraes.adapters.in.dtos.ResponseWrapper;
import dev.victormoraes.adapters.in.dtos.spot.SpotRequestDTO;
import dev.victormoraes.adapters.in.dtos.spot.SpotResponseDTO;
import dev.victormoraes.adapters.mappers.SpotMapper;
import dev.victormoraes.usecases.CreatingSpotUseCase;
import dev.victormoraes.usecases.DeletingSpotUseCase;
import dev.victormoraes.usecases.UpdatingSpotUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/spots")
public class SpotController {

    private final CreatingSpotUseCase useCase;

    private final UpdatingSpotUseCase updatingSpotUseCase;

    private final DeletingSpotUseCase deletingSpotUseCase;
    private final HttpServletRequest request;

    public SpotController(CreatingSpotUseCase useCase, UpdatingSpotUseCase updatingSpotUseCase, DeletingSpotUseCase deletingSpotUseCase, HttpServletRequest request) {
        this.useCase = useCase;
        this.updatingSpotUseCase = updatingSpotUseCase;
        this.deletingSpotUseCase = deletingSpotUseCase;
        this.request = request;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<SpotResponseDTO>> createSpot(
            @RequestBody SpotRequestDTO spotRequestDTO)
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
    public ResponseEntity<ResponseWrapper<SpotResponseDTO>> updateSpot(
            @PathVariable Long spotId,
            @RequestBody SpotRequestDTO spotRequestDTO) {

        var domainSpot = SpotMapper.toDomain(spotRequestDTO);
        var result = updatingSpotUseCase.updateSpot(spotId, domainSpot);

        if (!result.isSuccess()) {
            return ResponseEntity.unprocessableEntity().body(ResponseWrapper.error(result.getErrorMessage()));
        }

        SpotResponseDTO spotResponseDTO = SpotMapper.toResponseDTO(result.getResult());
        return ResponseEntity.ok(ResponseWrapper.success(spotResponseDTO));
    }

    @DeleteMapping("/{spotId}")
    public ResponseEntity<Void> deleteSpot(@PathVariable Long spotId) {
        var result = deletingSpotUseCase.deleteSpot(spotId);
        if (!result.isSuccess()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
