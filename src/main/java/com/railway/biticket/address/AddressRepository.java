package com.railway.biticket.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    boolean existsByName(String name);

    List<Address> findAddressesByLevel(Integer level);
}
