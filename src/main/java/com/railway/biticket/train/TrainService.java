package com.railway.biticket.train;

import com.railway.biticket.coach.CoachRepository;
import com.railway.biticket.common.Message;
import com.railway.biticket.common.exception.NotFoundException;
import com.railway.biticket.common.response.Response;
import com.railway.biticket.common.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class TrainService implements Message {
    private final TrainRepository trainRepository;

    public Response<?> get(UUID id) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_TRAIN,
                                Train.class));
        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(train)
                .build();
    }

    public Response<?> create(Train train) {
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
                .build();
    }

    public Response<?> getAll() {
        List<Train> all = trainRepository.findAll();

        if (all.size() == 0)
            return Response.builder()
                    .message(HttpStatus.NO_CONTENT.getReasonPhrase())
                    .statusCode(HttpStatus.NO_CONTENT.value())
                    .data(all)
                    .build();

        return Response.builder()
                .message(HttpStatus.FOUND.getReasonPhrase())
                .statusCode(HttpStatus.NO_CONTENT.value())
                .data(all)
                .build();

    }
}
