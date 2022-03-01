package com.railway.biticket.trip;

import com.railway.biticket.station.Station;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "trip_station",
            joinColumns = { @JoinColumn(name = "trip_id")},
            inverseJoinColumns = { @JoinColumn(name = "station_id") }
    )

    Set<Station> stations = new HashSet<>();

    public Set<Station> getStations() {
        return stations;
    }
}
