package com.example.hotelmanagement.dto.request;

import com.example.hotelmanagement.entity.room.RoomType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomRequestDto {

    @NotEmpty(message = "This number must be empty")
    @Column(nullable = false,unique = true)
    private String number;

    @NotEmpty(message = "This bed must be empty")
    @Column(nullable = false)
    private Integer bed;

    private Boolean hasTV;

    @NotEmpty(message = "This price must be empty")
    @Column(nullable = false)
    private Double price;

    @NotEmpty(message = "This room type must be empty")
    @Column(nullable = false)
    private RoomType type;

}
