package com.railway.biticket.seat;

import com.railway.biticket.coach.Coach;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Column(name = "seat_num", nullable = false)
    private Integer seatNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_coach_id")
    private Coach coach;
}
