package com.railway.biticket.coach;

import com.railway.biticket.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coach")
public class CoachController {
    private final CoachService coachService;

    @GetMapping("/list")
    public ResponseEntity<Response<?>> getList() {
        return ResponseEntity.ok()
                .body(coachService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> get(@PathVariable UUID id) {
        return ResponseEntity.ok().body(coachService.get(id));
    }

    @PostMapping
    public ResponseEntity<Response<?>> add(@RequestBody CoachDTO coachDTO) {
        return ResponseEntity.ok().body(coachService.create(coachDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable UUID id) {
        return ResponseEntity.ok().body(coachService.deleteById(id));
    }
}
