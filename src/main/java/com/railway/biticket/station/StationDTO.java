package com.railway.biticket.station;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.railway.biticket.address.Address;
import lombok.Getter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
public class StationDTO {
    private UUID id;
    private String name;
    private Double latitude;
    private Double longitude;

    @Size(min = 32, max = 32)
    @JsonProperty(value = "region_id")
    private UUID regionId;

    @Size(min = 32, max = 32)
    @JsonProperty(value = "district_id")
    private UUID districtId;
}
