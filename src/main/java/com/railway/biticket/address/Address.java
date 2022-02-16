package com.railway.biticket.address;

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
@Entity(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank
    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address region;

    @OneToMany(mappedBy = "region")
    private Set<Address> district = new HashSet<>();


}
