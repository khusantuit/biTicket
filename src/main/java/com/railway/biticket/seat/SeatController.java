package com.railway.biticket.seat;

import com.railway.biticket.common.response.Response;
import com.railway.biticket.station.Station;
import com.railway.biticket.station.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/list")
    public ResponseEntity<Response<?>> getList() {
        return ResponseEntity.ok().body(seatService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> get(
            @PathVariable UUID id) {
        return ResponseEntity.ok().body(seatService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> update(
            @PathVariable UUID id,
            @RequestBody SeatDTO seatDTO) {
        return ResponseEntity.ok().body(seatService.updateById(id, seatDTO));
    }

    @PostMapping
    public ResponseEntity<Response<?>> add(
            @RequestBody SeatDTO seatDTO
    ) {
        return ResponseEntity.ok().body(seatService.create(seatDTO));
    }
}
