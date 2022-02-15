package com.railway.biticket.user.passanger;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "passenger_data")
public class PassengerData {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @NotBlank
    @Column(name = "document_serial_number")
    private String documentSerialNumber;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;


    @NotNull
    private String middleName;

    @NotNull
    @Column(name = "issue_date")
    private LocalDate dateOfIssue;

    @NotNull
    @Column(name = "expire_date")
    private LocalDate dateOfExpire;
}
