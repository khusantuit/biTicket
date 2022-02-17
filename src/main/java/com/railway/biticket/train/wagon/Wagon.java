package com.railway.biticket.train.wagon;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Wagon {

    public Wagon() {
    }

    public Wagon(WagonType type, short capacity, String number) {
        this.type = type;
        this.capacity = capacity;
        this.number = number;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private WagonType type;


    @NotNull
    @Column(name = "capacity")
    private short capacity;


    @NotBlank
    @Column(name = "number", unique = true)
    private String number;

}
