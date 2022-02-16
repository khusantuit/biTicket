package com.railway.biticket.station;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.railway.biticket.address.Address;
import com.railway.biticket.route.Route;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "stations")
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
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "latitude", unique = true, nullable = false)
    private double latitude;

    @Column(name = "longitude", unique = true, nullable = false)
    private double longitude;

    @JsonIgnore
    @ManyToMany(mappedBy = "stations", cascade = CascadeType.ALL)
    private Set<Route> routes = new HashSet<>();
}
