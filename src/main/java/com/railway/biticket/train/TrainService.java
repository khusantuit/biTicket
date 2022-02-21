package com.railway.biticket.train;

import com.railway.biticket.coach.CoachRepository;
import com.railway.biticket.common.BaseService;
import com.railway.biticket.common.Message;
import com.railway.biticket.common.exception.NotFoundException;
import com.railway.biticket.common.response.Response;
import com.railway.biticket.common.exception.ConflictException;
import com.railway.biticket.seat.Seat;
import com.railway.biticket.seat.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class TrainService implements BaseService, Message {
    private final TrainRepository trainRepository;
    private final CoachRepository coachRepository;
    private final SeatRepository seatRepository;

    public ResponseEntity<Response<?>> create(Train train) {

        if (trainRepository.existsByNameIgnoreCase(train.getName()))
            throw new ConflictException(
                    NAME_CONFLICT_MSG,
                    Train.class,
                    "name");

        Train createdTrain = trainRepository.save(train);

        return Response.builder()
                .message(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value())
                .data(createdTrain.getId())
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> get(UUID id) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_TRAIN,
                                Train.class));

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(train)
                .build().makeResponseEntity();

    }

    public ResponseEntity<Response<?>> getAll() {
        List<Train> all = trainRepository.findAll();

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
            TrainDTO trainDTO
    ) {
        if(isEmpty(trainDTO.getName())) {
            return Response.builder()
                    .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .data(trainDTO)
                    .build().makeResponseEntity();
        }

        Train train = trainRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_TRAIN,
                                Train.class));

        if(!train.getName().equals(trainDTO.getName())) {
            train.setName(trainDTO.getName());

            trainRepository.save(train);
        }

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(train)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> deleteById(
            UUID trainId
    ) {
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_TRAIN,
                                Train.class));

        trainRepository.delete(train);

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build().makeResponseEntity();
    }
}
