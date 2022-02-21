package com.railway.biticket.address;

import com.railway.biticket.common.response.Response;
import com.railway.biticket.trip.TripDTO;
import com.railway.biticket.trip.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/list")
    public ResponseEntity<Response<?>> getList() {
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> get(
            @PathVariable UUID id
    ) {
        return addressService.get(id);
    }

    @PostMapping
    public ResponseEntity<Response<?>> add(@RequestBody AddressDTO addressDTO) {
        return addressService.create(addressDTO);
    }

//    @PutMapping("/{tripId}/station/add/{stationId}")
//    public ResponseEntity<Response<?>> add(
//            @PathVariable UUID tripId,
//            @PathVariable UUID stationId
//    ) {
//        return tripService.addStation(tripId, stationId);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> update(
            @PathVariable UUID id,
            @RequestBody AddressDTO addressDTO
    ) {
        return addressService.updateById(id, addressDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(
            @PathVariable UUID id
    ) {
        return addressService.deleteById(id);
    }
}
