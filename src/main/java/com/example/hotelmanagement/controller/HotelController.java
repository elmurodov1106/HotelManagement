package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.dto.request.HotelRequestDto;
import com.example.hotelmanagement.entity.hotel.HotelEntity;
import com.example.hotelmanagement.exception.RequestValidationException;
import com.example.hotelmanagement.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hotel")
public class HotelController {
    private final HotelService hotelService;

    @PostMapping("/create")
    public ResponseEntity<HotelEntity> addHotel(
            @Valid @RequestBody HotelRequestDto hotelDto,
            BindingResult bindingResult,
            Principal principal
    ){
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(hotelService.save(hotelDto, principal));
    }

    @DeleteMapping("/{hotelId}/delete")
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public ResponseEntity<String> deleteHotel(
            @PathVariable UUID hotelId
    ){
        hotelService.deleteById(hotelId);
        return ResponseEntity.ok("Successfully deleted");
    }

    @PatchMapping("/{hotelId}/update")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<HotelEntity> updateHotel(
            @PathVariable UUID hotelId,
            @RequestBody HotelRequestDto hotelRequestDto
    ){
        return ResponseEntity.ok(hotelService.update(hotelRequestDto,hotelId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<HotelEntity>> getAll(
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int size
    ){
        return ResponseEntity.status(200).body(hotelService.getAll(page, size));
    }








}
