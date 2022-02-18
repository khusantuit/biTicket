package com.railway.biticket.coach;

import com.railway.biticket.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/coach")
public class CoachController {
    private final CoachRepository coachRepository;

    public CoachController(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<Response<List<Coach>>> getList() {
        return ResponseEntity.ok().body(new Response<List<Coach>>("ok", 200, coachRepository.findAll()));
    }

    @PostMapping
    public ResponseEntity<Response<Coach>> add(
            @RequestBody Coach coach
    ) {
        return ResponseEntity.ok().body(new Response<Coach>("ok", 200, coachRepository.save(coach)));
    }
}
