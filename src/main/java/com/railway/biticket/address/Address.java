package com.railway.biticket.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.railway.biticket.coach.Coach;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Column(name = "name", unique = true)
    private String name;

//    private Integer parentId;

    @Column(name = "latitude", unique = true)
    private Double latitude;

    @Column(name = "longitude", unique = true)
    private Double longitude;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "parent_id")
//    private Address address;
//TODO

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name="parent_id")
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy="address")
    private List<Address> subordinates = new ArrayList<Address>();
}
