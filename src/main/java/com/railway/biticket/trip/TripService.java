package com.railway.biticket.trip;

import com.railway.biticket.common.response.Response;
import com.railway.biticket.station.Station;
import com.railway.biticket.station.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


@RequiredArgsConstructor
@Service
public class TripService {
    private final String LESS_THAN_TWO_STATIONS = "Less than two stations installed";
    private final String STATION_NOT_FOUND = " stationId is not found";
    private final String SUCCESS = "SUCCESS";

    private final StationRepository stationRepository;
    private final TripRepository tripRepository;


    public Trip addStation(UUID tripId, UUID stationId) {
        Optional<Station> station = stationRepository.findById(stationId);

        if(station.isEmpty())
            return null;

        Optional<Trip> trip = tripRepository.findById(tripId);

        if(trip.isEmpty())
            return null;

        Set<Station> stations = trip.get().getStations();
        stations.add(station.get());
        tripRepository.save(trip.get());
        return trip.get();
    }

    public ResponseEntity<?> add(TripDTO body) {
        Set<Station> stationSet = null;
        AtomicInteger count = new AtomicInteger(0);
        try {
            for (UUID uuid : body.getStations()) {
                Optional<Station> station = stationRepository.findById(uuid);

                // req da jo'natilgan stationlar uuid lar topilmasa bazadan
                if(station.isEmpty())
                    return ResponseEntity.badRequest().body(new Response<Trip>(uuid + STATION_NOT_FOUND, HttpServletResponse.SC_BAD_REQUEST));

                stationSet = new HashSet<>();
                stationSet.add(station.get());
                count.getAndIncrement();
            }

            // 1ta trip kamida 2 ta bo'lishi shart
            if (count.get() < 2)
                return ResponseEntity.badRequest().body(new Response<Trip>(LESS_THAN_TWO_STATIONS, HttpServletResponse.SC_BAD_REQUEST));

            Trip trip = new Trip();
            trip.setName(body.getName());
            trip.setStations(stationSet);

            return ResponseEntity.ok().body(new Response<Trip>(SUCCESS, HttpServletResponse.SC_OK, tripRepository.save(trip)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Response<>(e.getMessage(), HttpServletResponse.SC_BAD_REQUEST));
        }

    }
}
