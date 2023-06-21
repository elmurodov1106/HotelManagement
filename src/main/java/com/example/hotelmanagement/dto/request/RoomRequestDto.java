package com.example.hotelmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomRequestDto {
    @NotBlank(message = "Room number cannot be empty")
    private String number;
    @NotBlank(message = "Bed number cannot be empty")
    private String bed;
    @NotNull(message = "You should fill field TV")
    private Boolean hasTV;
    @NotNull(message = "Price cannot be empty")
    private Double price;
    @NotBlank(message = "Room type cannot be empty")
    private String type;

}
