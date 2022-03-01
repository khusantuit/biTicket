package com.railway.biticket.trip;

import com.railway.biticket.common.response.Response;
import lombok.RequiredArgsConstructor;
import model.recieve.TripDTO;
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
