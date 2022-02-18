package com.railway.biticket.coach;

import com.railway.biticket.seat.Seat;
import com.railway.biticket.train.Train;
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
@Entity(name = "coachs")
public class Coach {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "coach")
    private List<Seat> items = new ArrayList<Seat>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_train_id")
    private Train train;
}
