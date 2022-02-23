package com.railway.biticket.booking;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
public class BookingDTO {
    private UUID id;

    @Size(max = 32, min = 32)
    @NotNull
    private UUID passengerId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
