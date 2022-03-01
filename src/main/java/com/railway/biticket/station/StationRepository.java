package com.railway.biticket.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StationRepository extends JpaRepository<Station, UUID> {
    boolean existsByName(String name);

//    @Query(value = "SELECT * FROM stations s1, stations s2 where", nativeQuery = true)
//    @Query(value = "select * from stations s1, stations s2 where s1.id=, nativeQuery = true)
//    List<Station> getParentList();
}
