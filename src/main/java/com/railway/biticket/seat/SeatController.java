package com.railway.biticket.seat;

import com.railway.biticket.station.Station;
import com.railway.biticket.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatController(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @GetMapping("/list")
    public List<Seat> getList() {
        return seatRepository.findAll();
    }

    @PostMapping
    public Seat add(
            @RequestBody Seat seat
    ) {
        return seatRepository.save(seat);
    }
}
