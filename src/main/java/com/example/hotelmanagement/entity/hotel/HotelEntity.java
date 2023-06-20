package com.example.hotelmanagement.entity.hotel;

import com.example.hotelmanagement.entity.BaseEntity;
import com.example.hotelmanagement.entity.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity(name = "hotel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HotelEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique = true)
    private String website;
    @OneToOne
    private UserEntity owner;
}
