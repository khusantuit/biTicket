package com.railway.biticket.user;

import com.railway.biticket.common.response.Response;
import com.railway.biticket.station.StationService;
import lombok.RequiredArgsConstructor;
import model.recieve.StationDTO;
import model.recieve.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<Response<?>> getList() {
        return userService.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> get(
            @PathVariable UUID id
    ) {

        return userService.get(id);
    }

    @PostMapping
    public ResponseEntity<Response<?>> add(@RequestBody @Valid UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> update(
            @PathVariable UUID id,
            @RequestBody @Valid UserDTO userDTO
    ) {
        return userService.updateById(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(
            @PathVariable UUID id
    ) {
        return userService.deleteById(id);
    }
}
