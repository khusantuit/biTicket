package com.railway.biticket.train;

import com.railway.biticket.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/train")
public class TrainController {

    private final TrainService trainService;

    @GetMapping("/list")
    public Response<?> getList() {
        return trainService.getAll();
    }

    @GetMapping("/{id}")
    public Response<?> get(@PathVariable UUID id) {
        return trainService.get(id);
    }

    @PostMapping
    public Response<?> add(@RequestBody @Valid Train train) {
        return trainService.create(train);
    }

}
