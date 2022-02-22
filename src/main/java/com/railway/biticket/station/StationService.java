package com.railway.biticket.station;

import com.railway.biticket.address.Address;
import com.railway.biticket.address.AddressRepository;
import com.railway.biticket.common.BaseService;
import com.railway.biticket.common.Message;
import com.railway.biticket.common.exception.ConflictException;
import com.railway.biticket.common.exception.NotFoundException;
import com.railway.biticket.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StationService implements BaseService, Message {
    private final StationRepository stationRepository;
    private final AddressRepository addressRepository;

    public ResponseEntity<Response<?>> create(StationDTO stationDTO) {
        if(isEmpty(stationDTO.getName()))
            return Response.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .data(stationDTO)
                    .build().makeResponseEntity();

        if(stationRepository.existsByName(stationDTO.getName()))
            throw new ConflictException(
                    NAME_CONFLICT_MSG,
                    Station.class,
                    "name"
            );

        Address region = addressRepository.findById(stationDTO.getRegionId()).orElseThrow(()-> new NotFoundException(NOT_FOUND_ADDRESS, Address.class));
        Address district = addressRepository.findById(stationDTO.getDistrictId()).orElseThrow(()-> new NotFoundException(NOT_FOUND_ADDRESS, Address.class));

        Station station = Station.builder()
                .name(stationDTO.getName())
                .region(region)
                .district(district)
                .build();

        Station saved = stationRepository.save(station);

        return Response.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(saved.getId())
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> get(UUID id) {

        Station station = stationRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_STATION,
                                Station.class));

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(station)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> getAll() {
        List<Station> all = stationRepository.findAll();

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
            StationDTO stationDTO
    ) {
        if(isEmpty(stationDTO.getName()))
            return Response.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .data(stationDTO)
                    .build().makeResponseEntity();

        Station station = stationRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_STATION,
                                Station.class));

        if(!station.getName().equals(stationDTO.getName())) {
            station.setName(stationDTO.getName());

            stationRepository.save(station);
        }

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(station)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> deleteById(
            UUID stationId
    ) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_STATION,
                                Station.class));

        stationRepository.delete(station);

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build().makeResponseEntity();
    }

}
