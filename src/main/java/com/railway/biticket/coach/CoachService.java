package com.railway.biticket.coach;

import com.railway.biticket.common.Message;
import com.railway.biticket.common.exception.ConflictException;
import com.railway.biticket.common.response.Response;
import com.railway.biticket.train.Train;
import com.railway.biticket.train.TrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CoachService implements Message {
    private final CoachRepository coachRepository;
    private final TrainRepository trainRepository;

   public Response<Coach> create(CoachDTO coachDTO) {
       if(coachRepository.existsByNameIgnoreCase(coachDTO.getName()))
           throw new ConflictException(
                   NAME_CONFLICT_MSG,
                   Coach.class,
                   "name"
           );

       Optional<Train> optionalTrain = trainRepository.findById(coachDTO.getTrainId());

       Coach coach = Coach.builder()
               .name(coachDTO.getName()).build();

       return coachRepository.save(coachDTO);
   }

}
