package com.railway.biticket.station;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    private final StationRepository stationRepository;

    @Autowired
    public StationController(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @GetMapping("/list")
    public List<Station> getList() {
        return stationRepository.findAll();
    }

    @PostMapping
    public Station add(
            @RequestBody Station station
    ) {
        return stationRepository.save(station);
    }
}
