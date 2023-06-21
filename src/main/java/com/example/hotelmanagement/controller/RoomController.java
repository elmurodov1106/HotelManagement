package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.dto.request.RoomRequestDto;
import com.example.hotelmanagement.entity.room.RoomEntity;
import com.example.hotelmanagement.exception.RequestValidationException;
import com.example.hotelmanagement.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<RoomEntity> add(
          @Valid @RequestBody RoomRequestDto room,
            BindingResult bindingResult
            ){
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(roomService.save(room));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<RoomEntity>> getAll(
            @RequestParam(required = false,defaultValue = "1") int size,
            @RequestParam(required = false,defaultValue = "10") int page
    ){
        return ResponseEntity.status(200).body(roomService.getAll(size, page));
    }

    @PutMapping("/update")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<RoomEntity> update(
            @Valid @RequestBody RoomRequestDto update,
                   @RequestParam UUID roomId,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(roomService.update(update,roomId));
    }

    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity delete(
            @RequestParam UUID roomId
    ){
        roomService.delete(roomId);
       return ResponseEntity.status(200).build();
    }



}
