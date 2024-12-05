package io.github.yasirmaulana.healthcare.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HealthcareProductResponseDTO {
    private Long id;
    private String name;
    private String sku;
    private String imageUrl;
    private Double price;
    private String description;
    private String category;
}
