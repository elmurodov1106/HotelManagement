package com.example.hotelmanagement.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HotelRequestDto {
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotEmpty(message = "address cannot be empty")
    private String address;

    @NotEmpty(message = "website cannot be empty")
    private String website;
}
