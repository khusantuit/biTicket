package com.railway.biticket.station;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StationRepository extends JpaRepository<Station, UUID> {
}
