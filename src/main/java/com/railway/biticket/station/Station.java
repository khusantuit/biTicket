package com.railway.biticket.station;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.railway.biticket.address.Address;
import com.railway.biticket.trip.Trip;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stations")
public class Station {
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

    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonProperty(value = "region")
    private Address region;

    @ManyToOne
    @JoinColumn(name = "district_id")
    @JsonProperty(value = "district")
    private Address district;

    @Column(name = "latitude", unique = true)
    private Double latitude;

    @Column(name = "longitude", unique = true)
    private Double longitude;

    @JsonIgnore
    @ManyToMany(mappedBy = "stations", cascade = CascadeType.ALL)
    private Set<Trip> routes = new HashSet<>();
}
