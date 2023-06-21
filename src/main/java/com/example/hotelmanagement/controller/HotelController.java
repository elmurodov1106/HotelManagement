package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.dto.request.HotelRequestDto;
import com.example.hotelmanagement.entity.hotel.HotelEntity;
import com.example.hotelmanagement.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hotel")
public class HotelController {
    private final HotelService hotelService;

    @PostMapping("/add")
    public ResponseEntity<HotelEntity> addHotel(
            @RequestBody HotelRequestDto hotelDto
    ){
        return ResponseEntity.ok(hotelService.save(hotelDto));
    }

    @DeleteMapping("/{hotelId}delete")
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")

    public ResponseEntity deleteHotel(
            @PathVariable UUID hotelId
    ){
        hotelService.deleteById(hotelId);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/{hotelId}update")
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
