package com.railway.biticket.coach;

import com.railway.biticket.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CoachService {

    private final CoachRepository coachRepository;

   public Response<Coach> create(Coach coach) {
       return null;
   }

}
