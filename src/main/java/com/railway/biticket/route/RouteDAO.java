package com.railway.biticket.route;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.railway.biticket.address.Address;
import com.railway.biticket.route.Route;
import com.railway.biticket.station.Station;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
public class RouteDAO {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "station_id", nullable = false)
    private UUID stationId;
}
