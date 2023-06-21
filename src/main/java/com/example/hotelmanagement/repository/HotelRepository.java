package com.example.hotelmanagement.repository;

import com.example.hotelmanagement.entity.hotel.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, UUID> {

}
