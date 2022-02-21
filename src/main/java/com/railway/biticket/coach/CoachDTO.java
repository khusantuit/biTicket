package com.railway.biticket.coach;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoachDTO {
    private String name;

    @JsonProperty(value = "train_id")
    private UUID trainId;
}
