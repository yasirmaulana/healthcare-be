package io.github.yasirmaulana.healthcare.service;

import io.github.yasirmaulana.healthcare.dto.HealthcareProductRequestDTO;
import io.github.yasirmaulana.healthcare.dto.HealthcareProductResponseDTO;
import io.github.yasirmaulana.healthcare.dto.ResultPageResponseDTO;

import java.util.List;

public interface HealthcareProductService {
    void createHealthcareProduct(HealthcareProductRequestDTO dto);
    void createHealthcareProducts(List<HealthcareProductRequestDTO> dtos);
    void updateHealthcareProduct(Long productId, HealthcareProductRequestDTO dto);
    void deleteHealthcareProduct(Long id);
    HealthcareProductResponseDTO getHealtcareProductById(Long id);
    ResultPageResponseDTO<HealthcareProductResponseDTO> getHealthcareProductPage(Integer pages, Integer limit,
                                                                                 String sortBy, String direction, String productName);
}
