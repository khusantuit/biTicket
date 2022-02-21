package com.railway.biticket.station;

import com.railway.biticket.address.AddressDTO;
import com.railway.biticket.common.response.Response;
import com.railway.biticket.trip.TripDTO;
import com.railway.biticket.trip.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
