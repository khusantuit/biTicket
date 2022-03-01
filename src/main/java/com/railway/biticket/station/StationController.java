package com.railway.biticket.station;

import com.railway.biticket.common.response.Response;
import lombok.RequiredArgsConstructor;
import model.recieve.StationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/station")
public class StationController {
    private final StationService stationService;

    @GetMapping("/list")
    public ResponseEntity<Response<?>> getList() {
        return stationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> get(
            @PathVariable UUID id
    ) {
        return stationService.get(id);
    }

    @PostMapping
    public ResponseEntity<Response<?>> add(@RequestBody StationDTO stationDTO) {
        return stationService.create(stationDTO);
    }

    @PutMapping("/{tripId}/station/add/{stationId}")
    public ResponseEntity<Response<?>> add(
            @PathVariable UUID tripId,
            @PathVariable UUID stationId
    ) {
        return stationService.addStation(tripId, stationId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> update(
            @PathVariable UUID id,
            @RequestBody StationDTO stationDTO
    ) {
        return stationService.updateById(id, stationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(
            @PathVariable UUID id
    ) {
        return stationService.deleteById(id);
    }
}
