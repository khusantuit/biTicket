package com.railway.biticket.train;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {

    private final TrainRepository trainRepository;

    @Autowired
    public TrainController(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @GetMapping("/list")
    public List<Train> getList() {
        return trainRepository.findAll();
    }

    @PostMapping
    public Train add(
            @RequestBody Train train
    ) {
        return trainRepository.save(train);
    }
}
