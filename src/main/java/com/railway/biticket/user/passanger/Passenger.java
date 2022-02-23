package com.railway.biticket.user.passanger;

import com.railway.biticket.booking.Booking;
import com.railway.biticket.seat.Seat;
import com.railway.biticket.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private DocumentType documentType;

    @NotBlank
    @Column(name = "document_serial_number", nullable = false)
    private String documentSerialNumber;

    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @NotNull
    @Column(name = "issue_date", nullable = false)
    private LocalDate dateOfIssue;

    @NotNull
    @Column(name = "expire_date", nullable = false)
    private LocalDate dateOfExpire;

    @NotNull
    @Column(name = "state", nullable = false, columnDefinition = "int default 1")
    private int state;

    @OneToMany(mappedBy = "passenger")
    private List<Booking> bookings = new ArrayList<>();
}
