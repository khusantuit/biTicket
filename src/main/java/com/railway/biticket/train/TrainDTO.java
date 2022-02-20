package com.railway.biticket.train;

import com.railway.biticket.coach.Coach;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TrainDTO {
    private UUID id;

    private String name;

    private List<UUID> coaches = new ArrayList<>();
}
