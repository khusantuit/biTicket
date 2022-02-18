package com.railway.biticket.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/address")
public class AddressController {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/list")
    public List<Address> getList() {
        return addressRepository.findAll();
    }

    @PostMapping
    public Address add(
            @RequestBody Address address
    ) {
        return addressRepository.save(address);
    }
}
