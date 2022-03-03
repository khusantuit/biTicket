package com.railway.biticket.user;

import com.railway.biticket.common.Message;
import com.railway.biticket.common.exception.ConflictException;
import com.railway.biticket.common.exception.NotFoundException;
import com.railway.biticket.common.response.Response;
import com.railway.biticket.station.Station;
import lombok.RequiredArgsConstructor;
import model.recieve.UserDTO;
import model.recieve.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements Message {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ResponseEntity<Response<?>> create(UserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.getEmail()))
            throw new ConflictException(
                    EMAIL_CONFLICT_MSG,
                    Station.class,
                    "email"
            );
        User user = new ModelMapper().map(userDTO, User.class);

        List<Role> list = new ArrayList<>();

        userDTO.getUserRoles().forEach((id) -> {
            try {
                list.add(roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role with id: " + id + " not found")));
            } catch (RoleNotFoundException e) {
                e.printStackTrace();
            }
        });

        user.setRoleEntities(list);

        User saved = userRepository.save(user);

        return Response.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(saved.getId())
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> get(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_USER,
                                User.class));

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(user)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> getAll() {
        List<User> all = userRepository.findAll();

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
            UserDTO userDTO
    ) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty())
                throw new NotFoundException(NOT_FOUND_USER, User.class);

        User user = new ModelMapper().map(userDTO, User.class);

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(user)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> deleteById(
            UUID userId
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_USER,
                                User.class));

        userRepository.delete(user);

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build().makeResponseEntity();
    }

}
