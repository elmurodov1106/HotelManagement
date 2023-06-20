package com.example.hotelmanagement.repository;

import com.example.hotelmanagement.entity.room.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
}
