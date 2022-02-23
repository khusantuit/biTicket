package com.railway.biticket.seat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.railway.biticket.booking.Booking;
import com.railway.biticket.booking.tripStation.BookingTripStation;
import com.railway.biticket.coach.Coach;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @JsonProperty(value = "seat_num")
    @Column(name = "seat_num", nullable = false)
    private int seatNum;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @JsonIgnore
    @OneToMany(mappedBy = "seat")
    private List<BookingTripStation> bookingTripStations = new ArrayList<>();


}
