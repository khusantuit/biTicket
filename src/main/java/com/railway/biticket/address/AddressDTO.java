package com.railway.biticket.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AddressDTO {
    private UUID id;
    private String name;
    private Double latitude;
    private Double longitude;
    @JsonProperty("parent_id")
    private UUID parentId;
}
