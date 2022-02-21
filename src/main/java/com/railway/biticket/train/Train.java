package com.railway.biticket.train;

import com.railway.biticket.coach.Coach;
import com.railway.biticket.seat.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity(name = "trains")
public class Train {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<Coach> coaches = new ArrayList<>();
}
