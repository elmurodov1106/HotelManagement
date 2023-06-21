package com.example.hotelmanagement.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HotelRequestDto {
    @NotEmpty(message = "name not be empty")
    private String name;

    @NotEmpty(message = "address not be empty")
    private String address;

    @NotEmpty(message = "web site not be empty")
    private String website;
}
