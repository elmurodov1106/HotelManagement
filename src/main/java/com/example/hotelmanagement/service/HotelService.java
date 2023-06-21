package com.example.hotelmanagement.service;

import com.example.hotelmanagement.dto.request.HotelRequestDto;
import com.example.hotelmanagement.entity.hotel.HotelEntity;
import com.example.hotelmanagement.exception.DataNotFoundException;
import com.example.hotelmanagement.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelService implements BaseService<HotelEntity, UUID> {

   private final HotelRepository hotelRepository;
   private final HotelService hotelService;
   private final ModelMapper modelMapper;

   public HotelEntity save(HotelRequestDto hotelRequestDto){
       HotelEntity hotel = modelMapper.map(hotelRequestDto,HotelEntity.class);
       return hotelRepository.save(hotel);
   }

   public List<HotelEntity> getAll(int page,int size){
       Pageable pageable = PageRequest.of(page,size);
        return hotelRepository.findAll(pageable).getContent();
   }

   public HotelEntity update(HotelRequestDto update,UUID id){
       HotelEntity hotel =hotelRepository.findById(id)
               .orElseThrow(() -> new DataNotFoundException("hotel not found"));
       modelMapper.map(update,hotel);
       return hotelRepository.save(hotel);
   }

   public void deleteById(UUID id){
       HotelEntity hotelEntity = hotelRepository.findById(id)
                       .orElseThrow(() -> new DataNotFoundException("This hotel not found"));
       hotelRepository.deleteById(id);
   }



}
