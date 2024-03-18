package dev.victormoraes.adapters.in;

import dev.victormoraes.adapters.in.dtos.ResponseWrapper;
import dev.victormoraes.adapters.in.dtos.spot.SpotRequestDTO;
import dev.victormoraes.adapters.in.dtos.spot.SpotResponseDTO;
import dev.victormoraes.adapters.mappers.SpotMapper;
import dev.victormoraes.usecases.CreatingSpotUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/spots")
public class SpotController {

    private final CreatingSpotUseCase useCase;
    private final HttpServletRequest request;

    public SpotController(CreatingSpotUseCase useCase, HttpServletRequest request) {
        this.useCase = useCase;
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
}
