package com.railway.biticket.train;

import com.railway.biticket.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/train")
public class TrainController {

    private final TrainService trainService;

    @GetMapping("/list")
    public ResponseEntity<Response<?>> getList() {
        return ResponseEntity.ok().body(trainService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(trainService.get(id));
    }

    @PostMapping
    public ResponseEntity<Response<?>> add(@RequestBody @Valid Train train) {
        return ResponseEntity.ok().body(trainService.create(train));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> update(
            @PathVariable UUID id,
            @RequestBody TrainDTO trainDTO
    ) {
        return ResponseEntity.ok().body(trainService.updateById(id, trainDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable UUID id) {
        return ResponseEntity.ok().body(trainService.deleteById(id));
    }
}
