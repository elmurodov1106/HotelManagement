package com.example.hotelmanagement.service;

import com.example.hotelmanagement.dto.request.RoomRequestDto;
import com.example.hotelmanagement.entity.hotel.HotelEntity;
import com.example.hotelmanagement.entity.room.RoomEntity;
import com.example.hotelmanagement.entity.room.RoomType;
import com.example.hotelmanagement.exception.DataNotFoundException;
import com.example.hotelmanagement.exception.UniqueObjectException;
import com.example.hotelmanagement.repository.HotelRepository;
import com.example.hotelmanagement.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;

    public RoomEntity save(RoomRequestDto roomRequestDto, UUID hotelId){
        HotelEntity hotelEntity = hotelRepository.findById(hotelId).orElseThrow(() -> new DataNotFoundException("Hotel not found"));
        RoomEntity roomEntity = modelMapper.map(roomRequestDto, RoomEntity.class);
        if(roomRepository.findRoomEntityByNumber(roomRequestDto.getNumber()).isEmpty()){
            roomEntity.setHotel(hotelEntity);
            if(Objects.equals(roomRequestDto.getType(), "LUXURY") || Objects.equals(roomRequestDto.getType(), "BUDGET"))  roomEntity.setType(RoomType.valueOf(roomRequestDto.getType()));
            else throw new DataNotFoundException("Invalid room type");
            return roomRepository.save(roomEntity);
        }
        throw new UniqueObjectException("Room number already exists");

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

    public RoomEntity
    update(RoomRequestDto roomRequestDto,UUID roomId){
        RoomEntity roomEntity = roomRepository.findById(roomId)
                .orElseThrow(() -> new DataNotFoundException("This room not found"));
        if(roomRequestDto.getNumber() != null){
            if(!roomRequestDto.getNumber().equals(roomEntity.getNumber())) {
                Optional<RoomEntity> roomEntityByNumber = roomRepository.findRoomEntityByNumber(roomRequestDto.getNumber());
                if (roomEntityByNumber.isPresent()){
                    throw new UniqueObjectException("Room number already exists");
                }
                roomEntity.setNumber(roomRequestDto.getNumber());
            }
        }
        if(roomRequestDto.getBed() != null) roomEntity.setBed(roomRequestDto.getBed());
        if(roomRequestDto.getHasTV() != null) roomEntity.setHasTV(roomRequestDto.getHasTV());
        if(roomRequestDto.getPrice() != null) roomEntity.setPrice(roomRequestDto.getPrice());
        if(roomRequestDto.getType() != null) {
            if (Objects.equals(roomRequestDto.getType(), "LUXURY") || Objects.equals(roomRequestDto.getType(), "BUDGET"))
                roomEntity.setType(RoomType.valueOf(roomRequestDto.getType()));
            else throw new DataNotFoundException("Invalid room type");
        }
        return roomRepository.save(roomEntity);
    }


}
