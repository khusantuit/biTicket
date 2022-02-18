package com.railway.biticket.trip;

import com.railway.biticket.Response;
import com.railway.biticket.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/trip")
public class TripController {

    private  final TripRepository tripRepository;
    private final TripService tripService;


    @Autowired
    public TripController(TripRepository tripRepository, StationRepository stationRepository, TripService tripService) {
        this.tripRepository = tripRepository;
        this.tripService = tripService;
    }

    @GetMapping("/list")
    public List<Trip> getList() {
        return tripRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody TripDTO body) {
        return tripService.add(body);
    }

    @PutMapping("/{tripId}/station/add/{stationId}")
    public Trip add(
            @PathVariable UUID tripId,
            @PathVariable UUID stationId
    ) {
        return tripService.addStation(tripId, stationId);
    }

}
