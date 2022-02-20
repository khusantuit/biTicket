package com.railway.biticket.seat;

import com.railway.biticket.coach.Coach;
import com.railway.biticket.coach.CoachDTO;
import com.railway.biticket.coach.CoachRepository;
import com.railway.biticket.common.Message;
import com.railway.biticket.common.exception.ConflictException;
import com.railway.biticket.common.exception.NotFoundException;
import com.railway.biticket.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeatService implements Message {
    private final SeatRepository seatRepository;
    private final CoachRepository coachRepository;

    public Response<?> create(SeatDTO seatDTO) {
        if(seatRepository.existsBySeatNum(seatDTO.getSeatNum()))
            throw new ConflictException(
                    NAME_CONFLICT_MSG,
                    Seat.class,
                    "seat_num"
            );

        Coach coach = coachRepository.findById(seatDTO.getCoachId()).orElseThrow(()-> new NotFoundException(NOT_FOUND_COACH, Coach.class));

        Seat seat = Seat.builder()
                .seatNum(seatDTO.getSeatNum())
                .coach(coach)
                .build();

        Seat saved = seatRepository.save(seat);

        return Response.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(saved.getId())
                .build();
    }

    public Response<?> get(UUID id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_SEAT,
                                Seat.class));

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(seat)
                .build();
    }

    public Response<?> getAll() {
        List<Seat> all = seatRepository.findAll();

        if (all.size() == 0)
            return Response.builder()
                    .message(HttpStatus.NO_CONTENT.getReasonPhrase())
                    .statusCode(HttpStatus.NO_CONTENT.value())
                    .data(all)
                    .build();

        return Response.builder()
                .message(HttpStatus.FOUND.getReasonPhrase())
                .statusCode(HttpStatus.FOUND.value())
                .data(all)
                .build();
    }

    public Response<?> updateById(
            UUID id,
            SeatDTO seatDTO
    ) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_SEAT,
                                Seat.class));

        if(seat.getSeatNum() != seatDTO.getSeatNum()) {
            seat.setSeatNum(seatDTO.getSeatNum());

            seatRepository.save(seat);
        }

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(seat)
                .build();
    }

    public Response<?> deleteById(
            UUID seatId
    ) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_SEAT,
                                Seat.class));

        seatRepository.delete(seat);

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
