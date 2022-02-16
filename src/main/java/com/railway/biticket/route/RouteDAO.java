package com.railway.biticket.route;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.railway.biticket.address.Address;
import com.railway.biticket.route.Route;
import com.railway.biticket.station.Station;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class RouteDAO {
    private UUID id;
    private String name;
    private List<UUID> stations;
}
