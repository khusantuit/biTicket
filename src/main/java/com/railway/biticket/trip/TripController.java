package com.railway.biticket.trip;

import com.railway.biticket.common.response.Response;
import com.railway.biticket.station.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;

    @GetMapping("/list")
    public ResponseEntity<Response<?>> getList() {
        return tripService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> get(
            @PathVariable UUID id
    ) {
        return tripService.get(id);
    }

    @PostMapping
    public ResponseEntity<Response<?>> add(@RequestBody TripDTO body) {
        return tripService.create(body);
    }

    @PutMapping("/{tripId}/station/add/{stationId}")
    public ResponseEntity<Response<?>> add(
            @PathVariable UUID tripId,
            @PathVariable UUID stationId
    ) {
        return tripService.addStation(tripId, stationId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> update(
            @PathVariable UUID id,
            @RequestBody TripDTO tripDTO
    ) {
        return tripService.updateById(id, tripDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(
            @PathVariable UUID id
    ) {
        return tripService.deleteById(id);
    }

}
