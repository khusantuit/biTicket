package com.railway.biticket.trip;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TripDTO {
    private UUID id;
    private String name;
    private List<UUID> stations;
}
