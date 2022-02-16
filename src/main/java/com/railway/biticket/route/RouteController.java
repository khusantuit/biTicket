package com.railway.biticket.route;

import com.railway.biticket.station.Station;
import com.railway.biticket.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/route")
public class RouteController {
    private  final RouteRepository routeRepository;
    private  final StationRepository stationRepository;


    @Autowired
    public RouteController(RouteRepository routeRepository, StationRepository stationRepository) {
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
    }

    @GetMapping("/list")
    public List<Route> getList() {
        return routeRepository.findAll();
    }

    @PostMapping("/add")
    public Route add(@RequestBody RouteDAO body) {
        System.out.println(body.toString());
        Optional<Station> byId = stationRepository.findById(body.getStationId());

        if(byId.isPresent()) {
            Route route = new Route();
            route.setName(body.getName());

            Set<Station> stationSet = Set.of(byId.get());
            route.setStations(stationSet);

            return routeRepository.save(route);
        }

        return null;
    }
}
