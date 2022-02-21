package com.railway.biticket.station;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.railway.biticket.address.Address;
import lombok.Getter;

import java.util.UUID;

@Getter
public class StationDTO {
    private UUID id;
    private String name;
    private Double latitude;
    private Double longitude;

    @JsonProperty(value = "address_id")
    private UUID addressId;
}
