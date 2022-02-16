package com.railway.biticket.route;

import com.railway.biticket.station.Station;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "routes")
public class Route {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotBlank
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "route_station",
            joinColumns = { @JoinColumn(name = "route_id") },
            inverseJoinColumns = { @JoinColumn(name = "station_id") }
    )
    Set<Station> stations = new HashSet<>();
}
