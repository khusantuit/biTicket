package com.railway.biticket.booking;

import com.railway.biticket.common.Message;
import com.railway.biticket.common.exception.NotFoundException;
import com.railway.biticket.common.response.Response;
import com.railway.biticket.user.passanger.Passenger;
import com.railway.biticket.user.passanger.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServise implements Message {
    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;

    public ResponseEntity<Response<?>> create(BookingDTO bookingDTO) {
//        if(isEmpty(bookingDTO.getSeatNum(), seatDTO.getCoachId()))
//            return Response.builder()
//                    .statusCode(HttpStatus.BAD_REQUEST.value())
//                    .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
//                    .data(seatDTO)
//                    .build().makeResponseEntity();

//        if(boo.existsBySeatNum(seatDTO.getSeatNum()))
//            throw new ConflictException(
//                    NAME_CONFLICT_MSG,
//                    Seat.class,
//                    "seat_num"
//            );

        Passenger passenger = passengerRepository.findById(bookingDTO.getPassengerId()).orElseThrow(()-> new NotFoundException(NOT_FOUND_PASSENGER, Passenger.class));

        Booking booking = Booking.builder()
                .passenger(passenger)
                .status(bookingDTO.getStatus())
                .build();

        Booking saved = bookingRepository.save(booking);

        return Response.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(saved.getId())
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> get(UUID id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_BOOKING,
                                Booking.class));

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(booking)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> getAll() {
        List<Booking> all = bookingRepository.findAll();

        if (all.size() == 0)
            return Response.builder()
                    .message(HttpStatus.NO_CONTENT.getReasonPhrase())
                    .statusCode(HttpStatus.NO_CONTENT.value())
                    .data(all)
                    .build().makeResponseEntity();

        return Response.builder()
                .message(HttpStatus.FOUND.getReasonPhrase())
                .statusCode(HttpStatus.FOUND.value())
                .data(all)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> updateById(
            UUID id,
            BookingDTO bookingDTO
    ) {
//        if(isEmpty(seatDTO.getSeatNum(), seatDTO.getCoachId()))
//            return Response.builder()
//                    .statusCode(HttpStatus.BAD_REQUEST.value())
//                    .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
//                    .data(seatDTO)
//                    .build().makeResponseEntity();

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_BOOKING,
                                Booking.class));

        if(booking.getStatus() != bookingDTO.getStatus()) {
            booking.setStatus(bookingDTO.getStatus());

            bookingRepository.save(booking);
        }

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(booking)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> deleteById(
            UUID bookingId
    ) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_BOOKING,
                                Booking.class));

        bookingRepository.delete(booking);

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build().makeResponseEntity();
    }
}
