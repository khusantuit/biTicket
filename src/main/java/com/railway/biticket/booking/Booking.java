package com.railway.biticket.booking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.railway.biticket.booking.tripStation.BookingTripStation;
import com.railway.biticket.coach.Coach;
import com.railway.biticket.user.passanger.Passenger;
import lombok.*;
import model.recieve.BookingStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @LastModifiedBy
    private String updatedBy;

    @JsonIgnore
    @OneToMany(mappedBy = "booking")
    private List<BookingTripStation> bookingTripStation = new ArrayList<>();

}
