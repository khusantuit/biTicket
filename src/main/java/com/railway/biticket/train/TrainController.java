package com.railway.biticket.train;

import com.railway.biticket.common.response.Response;
import lombok.RequiredArgsConstructor;
import model.recieve.TrainDTO;
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
        return trainService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> get(@PathVariable UUID id) {
        return trainService.get(id);
    }

    @PostMapping
    public ResponseEntity<Response<?>> add(@RequestBody @Valid Train train) {
        return trainService.create(train);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> update(
            @PathVariable UUID id,
            @RequestBody TrainDTO trainDTO
    ) {
        return trainService.updateById(id, trainDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable UUID id) {
        return trainService.deleteById(id);
    }
}
