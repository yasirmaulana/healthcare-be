package io.github.yasirmaulana.healthcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HealthcareProductRequestDTO {

    @NotBlank(message = "name must not be blank")
    private String name;

    @NotBlank(message = "sku must not be blank")
    private String sku;

    @NotBlank(message = "imageUrl must not be blank")
    private String imageUrl;

    @PositiveOrZero(message = "Price must be zero or positive")
    private Double price;

    @NotBlank(message = "description must not be blank")
    @Size(min = 5, max = 255, message = "description must be between 5 and 255 characters")
    private String description;

    @NotBlank(message = "category must not be blank")
    private String category;
}