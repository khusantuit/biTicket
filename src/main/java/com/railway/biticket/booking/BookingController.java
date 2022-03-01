package com.railway.biticket.booking;

import com.railway.biticket.common.response.Response;
import lombok.RequiredArgsConstructor;
import model.recieve.BookingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final BookingServise bookingServise;

    @GetMapping("/list")
    public ResponseEntity<Response<?>> getList() {
        return bookingServise.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> get(
            @PathVariable UUID id) {
        return bookingServise.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> update(
            @PathVariable UUID id,
            @RequestBody BookingDTO bookingDTO) {
        return bookingServise.updateById(id, bookingDTO);
    }

    @PostMapping
    public ResponseEntity<Response<?>> add(
            @RequestBody BookingDTO bookingDTO
    ) {
        return bookingServise.create(bookingDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(
            @PathVariable UUID id
    ) {
        return bookingServise.deleteById(id);
    }
}
