package com.railway.biticket.address;

import com.railway.biticket.common.response.Response;
import lombok.RequiredArgsConstructor;
import model.recieve.AddressDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/list")
    public ResponseEntity<Response<?>> getList(
            @RequestParam(required = false) Integer level
    ) {
        return addressService.getAll(level);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> get(
            @PathVariable UUID id
    ) {
        return addressService.get(id);
    }

    @PostMapping
    public ResponseEntity<Response<?>> add(@RequestBody @Valid AddressDTO addressDTO) {
        return addressService.create(addressDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> update(
            @PathVariable UUID id,
            @RequestBody @Valid AddressDTO addressDTO
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
