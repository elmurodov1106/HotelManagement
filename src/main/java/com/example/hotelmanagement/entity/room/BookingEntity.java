package com.example.hotelmanagement.entity.room;

import com.example.hotelmanagement.entity.BaseEntity;
import com.example.hotelmanagement.entity.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "booking")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingEntity extends BaseEntity {
    private LocalDate localDate;
    @ManyToOne
    private RoomEntity room;
    @OneToOne
    private UserEntity user;

}
