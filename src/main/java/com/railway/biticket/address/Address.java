package com.railway.biticket.address;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;

    @NotBlank
    @Column(name = "name", unique = true)
    private String name;

    private Integer parentId;

    @Column(name = "latitude", unique = true, nullable = false)
    private double latitude;

    @Column(name = "longitude", unique = true, nullable = false)
    private double longitude;
}
