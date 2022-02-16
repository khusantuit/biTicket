package com.railway.biticket.station;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/station")
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

    @PostMapping("/add")
    public Station add(
            @RequestBody Station station
    ) {
        return stationRepository.save(station);
    }
}
