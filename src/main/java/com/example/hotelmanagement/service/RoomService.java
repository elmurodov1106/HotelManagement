package com.example.hotelmanagement.service;

import com.example.hotelmanagement.dto.request.RoomRequestDto;
import com.example.hotelmanagement.entity.room.RoomEntity;
import com.example.hotelmanagement.exception.DataNotFoundException;
import com.example.hotelmanagement.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public RoomEntity save(RoomRequestDto roomRequestDto){
        RoomEntity roomEntity = modelMapper.map(roomRequestDto, RoomEntity.class);
        return roomRepository.save(roomEntity);

    }


    public List<RoomEntity> getAll(int size, int page ){
        Pageable pageable  = PageRequest.of(page, size);
        return roomRepository.findAll(pageable).getContent();
    }
    public void delete(UUID id){
        RoomEntity roomEntity = roomRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("This room not found"));
        roomRepository.delete(roomEntity);
    }

    public RoomEntity update(RoomRequestDto roomRequestDto,UUID roomId){
        RoomEntity roomEntity = roomRepository.findById(roomId)
                .orElseThrow(() -> new DataNotFoundException("This room not found"));
         modelMapper.map(roomRequestDto,roomEntity);
        return roomRepository.save(roomEntity);
    }


}
