package com.railway.biticket.coach;

import com.railway.biticket.common.BaseService;
import com.railway.biticket.common.Message;
import com.railway.biticket.common.exception.ConflictException;
import com.railway.biticket.common.exception.NotFoundException;
import com.railway.biticket.common.response.Response;
import com.railway.biticket.train.Train;
import com.railway.biticket.train.TrainRepository;
import lombok.RequiredArgsConstructor;
import model.recieve.CoachDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CoachService implements BaseService, Message {
    private final CoachRepository coachRepository;
    private final TrainRepository trainRepository;

   public ResponseEntity<Response<?>> create(CoachDTO coachDTO) {
       if(isEmpty(coachDTO.getName(), coachDTO.getTrainId())) {
           return Response.builder()
                   .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                   .statusCode(HttpStatus.BAD_REQUEST.value())
                   .data(coachDTO)
                   .build().makeResponseEntity();
       }

       if(coachRepository.existsByNameIgnoreCase(coachDTO.getName()))
           throw new ConflictException(
               NAME_CONFLICT_MSG,
               Coach.class,
               "name"
           );

       Train train = trainRepository.findById(coachDTO.getTrainId()).orElseThrow(()-> new NotFoundException(NOT_FOUND_TRAIN, Train.class));

       Coach coach = Coach.builder()
           .name(coachDTO.getName())
           .train(train)
           .build();

       Coach saved = null;

       try {
           saved = coachRepository.save(coach);
       } catch (Exception e) {
           e.printStackTrace();
           throw new ConflictException(
                   HttpStatus.BAD_REQUEST.getReasonPhrase(),
                   Coach.class,
                   "name"
           );
       }

       return Response.builder()
           .statusCode(HttpStatus.CREATED.value())
           .message(HttpStatus.CREATED.getReasonPhrase())
           .data(saved.getId())
           .build().makeResponseEntity();
   }

    public ResponseEntity<Response<?>> get(UUID id) {
        Coach coach = coachRepository.findById(id)
            .orElseThrow(() ->
                new NotFoundException(
                    NOT_FOUND_COACH,
                    Coach.class));

        return Response.builder()
            .message(HttpStatus.OK.getReasonPhrase())
            .statusCode(HttpStatus.OK.value())
            .data(coach)
            .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> getAll() {
        List<Coach> all = coachRepository.findAll();

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
            CoachDTO coachDTO
    ) {
        Coach coach = coachRepository.findById(id)
            .orElseThrow(() ->
                new NotFoundException(
                    NOT_FOUND_COACH,
                    Train.class));

        if(!coach.getName().equals(coachDTO.getName())) {
            coach.setName(coachDTO.getName());

            coachRepository.save(coach);
        }

        return Response.builder()
            .message(HttpStatus.OK.getReasonPhrase())
            .statusCode(HttpStatus.OK.value())
            .data(coach)
            .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> deleteById(
            UUID coachId
    ) {
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_COACH,
                                Coach.class));

        coachRepository.delete(coach);

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build().makeResponseEntity();
    }

}
