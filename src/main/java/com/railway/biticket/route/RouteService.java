package com.railway.biticket.route;

import com.railway.biticket.station.Station;
import com.railway.biticket.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RouteService {
    private final StationRepository stationRepository;
    private final RouteRepository routeRepository;


    @Autowired
    public RouteService(StationRepository stationRepository, RouteRepository routeRepository) {
        this.stationRepository = stationRepository;
        this.routeRepository = routeRepository;
    }

    public Route addStation(UUID routeId, UUID stationId) {
        Station station = stationRepository.getById(stationId);

        if(station != null) {
            Route route = routeRepository.getById(routeId);

            if(route != null) {
                Set<Station> stations = route.getStations();
                stations.add(station);
                routeRepository.save(route);
                return route;
            } else
                return null;
        }  else
            return null;
    }

    public Route add(RouteDAO body) {
        Set<Station> stationSet = null;
        AtomicInteger count = new AtomicInteger(0);
        for (UUID uuid : body.getStations()) {
            Optional<Station> station = stationRepository.findById(uuid);

            if(station.isPresent()) {
                stationSet = new HashSet<>();
                stationSet.add(station.get());
                count.getAndIncrement();
            } else {
                // req da jo'natilgan stationlar uuid lar topilmasa bazadan
                return null;
            }
        }

        // 1ta route kamida 2 ta bo'lishi shart
        if (count.get() < 2)
            return null;

        Route route = new Route();
        route.setName(body.getName());
        route.setStations(stationSet);

        return routeRepository.save(route);
    }
}
