package com.railway.biticket.seat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class SeatDTO {
    @JsonProperty(value = "seat_num")
    private int seatNum;
    @JsonProperty(value = "coach_id")
    private UUID coachId;
}
