package com.railway.biticket.trip;

import com.railway.biticket.common.BaseService;
import com.railway.biticket.common.Message;
import com.railway.biticket.common.exception.NotFoundException;
import com.railway.biticket.common.response.Response;
import com.railway.biticket.station.Station;
import com.railway.biticket.station.StationRepository;
import lombok.RequiredArgsConstructor;
import model.recieve.TripDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Service
public class TripService implements BaseService, Message {
    private final StationRepository stationRepository;
    private final TripRepository tripRepository;

    public ResponseEntity<Response<?>> create(TripDTO tripDTO) {
        if(isEmpty(tripDTO.getName(), tripDTO.getStations()))
            return Response.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .data(tripDTO)
                    .build().makeResponseEntity();

        Set<Station> stationSet = null;
        AtomicInteger count = new AtomicInteger(0);

        try {
            for (UUID uuid : tripDTO.getStations()) {
                Optional<Station> station = stationRepository.findById(uuid);

                // req da jo'natilgan stationlar uuid lar topilmasa bazadan
                if(station.isEmpty())
                    return Response.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(NOT_FOUND_STATION)
                            .data(tripDTO)
                            .build().makeResponseEntity();

                stationSet = new HashSet<>();
                stationSet.add(station.get());
                count.getAndIncrement();
            }

            // 1ta trip kamida 2 ta station bo'lishi shart
            if (count.get() < 2)
                return Response.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message(LESS_THAN_TWO_STATIONS)
                        .data(tripDTO)
                        .build().makeResponseEntity();

            Trip trip = new Trip();
            trip.setName(tripDTO.getName());
            trip.setStations(stationSet);
            Trip saved = tripRepository.save(trip);

            return Response.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .data(saved.getId())
                    .build().makeResponseEntity();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build().makeResponseEntity();
        }
    }

    public ResponseEntity<Response<?>> get(UUID id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_TRIP,
                                Trip.class));

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(trip)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> getAll() {
        List<Trip> all = tripRepository.findAll();

        if (all.size() == 0)
            return Response.builder()
                    .message(HttpStatus.NO_CONTENT.getReasonPhrase())
                    .statusCode(HttpStatus.NO_CONTENT.value())
                    .data(all)
                    .build().makeResponseEntity();

        return Response.builder()
                .message(HttpStatus.FOUND.getReasonPhrase())
                .statusCode(HttpStatus.FOUND.value())
                .data(all)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> updateById(
            UUID id,
            TripDTO tripDTO
    ) {
        if(isEmpty(tripDTO.getName(), tripDTO.getStations()))
            return Response.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .data(tripDTO)
                    .build().makeResponseEntity();

        Trip trip = tripRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_TRIP,
                                Trip.class));

        if(!trip.getName().equals(tripDTO.getName())) {
            trip.setName(tripDTO.getName());

            tripRepository.save(trip);
        }

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(trip)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> deleteById(
            UUID tripId
    ) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_TRIP,
                                Trip.class));

        tripRepository.delete(trip);

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build().makeResponseEntity();
    }

}
