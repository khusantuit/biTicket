package com.railway.biticket.address;

import com.railway.biticket.common.BaseService;
import com.railway.biticket.common.Message;
import com.railway.biticket.common.exception.ConflictException;
import com.railway.biticket.common.exception.NotFoundException;
import com.railway.biticket.common.response.Response;
import lombok.RequiredArgsConstructor;
import model.recieve.AddressDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AddressService implements BaseService, Message {
    private final AddressRepository addressRepository;

    public ResponseEntity<Response<?>> create(AddressDTO addressDTO) {
        if(addressRepository.existsByName(addressDTO.getName()))
            throw new ConflictException(
                    NAME_CONFLICT_MSG,
                    Address.class,
                    "name"
            );

        Address address = new ModelMapper().map(addressDTO, Address.class);

        if(addressDTO.getLatitude() != null && addressDTO.getLongitude() != null) {
            address.setLatitude(addressDTO.getLatitude());
            address.setLongitude(addressDTO.getLongitude());
        }

        if(addressDTO.getParentId() != null) {
            Optional<Address> parentAddress = addressRepository.findById(addressDTO.getParentId());

            if(parentAddress.isPresent())
                if(parentAddress.get().getLevel() < address.getLevel())
                    address.setAddress(parentAddress.get());
                else
                    return Response.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(HttpStatus.BAD_REQUEST.getReasonPhrase() + ". The level of parent address must be greater than current address level.")
                            .data(addressDTO)
                            .build().makeResponseEntity();
        }

        Address saved = addressRepository.save(address);

        return Response.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(saved.getId())
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> get(UUID id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_ADDRESS,
                                Address.class));

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(address)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> getAll(
            Integer level
    ) {
        List<Address> all;

        if(level == null)
            all = addressRepository.findAll();
        else
            all = addressRepository.findAddressesByLevel(level);

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
            @Valid AddressDTO addressDTO
    ) {

        if(isEmpty(addressDTO.getName()))
            return Response.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .data(addressDTO)
                    .build().makeResponseEntity();

        Address address = addressRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_ADDRESS,
                                Address.class));

        if(!address.getName().equals(addressDTO.getName())) {
            address.setName(addressDTO.getName());

            addressRepository.save(address);
        }

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(address)
                .build().makeResponseEntity();
    }

    public ResponseEntity<Response<?>> deleteById(
            UUID addressId
    ) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() ->
                        new NotFoundException(
                                NOT_FOUND_ADDRESS,
                                Address.class));

        addressRepository.delete(address);

        return Response.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build().makeResponseEntity();
    }
}
