package com.railway.biticket.coach;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CoachDTO {
    private String name;

    @JsonProperty(value = "train_id")
    private UUID trainId;
}
