package com.railway.biticket.route;

import com.railway.biticket.station.Station;
import com.railway.biticket.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/route")
public class RouteController {
    private  final RouteRepository routeRepository;
    private final RouteService routeService;


    @Autowired
    public RouteController(RouteRepository routeRepository, StationRepository stationRepository, RouteService routeService) {
        this.routeRepository = routeRepository;
        this.routeService = routeService;
    }

    @GetMapping("/list")
    public List<Route> getList() {
        return routeRepository.findAll();
    }

    @PostMapping("/add")
    public Route add(@RequestBody RouteDAO body) {
        return routeService.add(body);
    }

    @PutMapping("/{routeId}/station/add/{stationId}")
    public Route add(
            @PathVariable UUID routeId,
            @PathVariable UUID stationId
    ) {
        return routeService.addStation(routeId, stationId);
    }

}
