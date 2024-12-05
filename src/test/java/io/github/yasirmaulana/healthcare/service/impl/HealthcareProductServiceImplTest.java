package io.github.yasirmaulana.healthcare.service.impl;

import io.github.yasirmaulana.healthcare.domain.HealthcareProduct;
import io.github.yasirmaulana.healthcare.dto.HealthcareProductRequestDTO;
import io.github.yasirmaulana.healthcare.dto.HealthcareProductResponseDTO;
import io.github.yasirmaulana.healthcare.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.healthcare.exception.NoFoundException;
import io.github.yasirmaulana.healthcare.repository.HealthcareProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HealthcareProductServiceImplTest {

    @Mock
    private HealthcareProductRepository healthcareProductRepository;

    @InjectMocks
    private HealthcareProductServiceImpl healthcareProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateHealthcareProducts() {
        HealthcareProductRequestDTO dto = HealthcareProductRequestDTO.builder()
                .name("Test Product")
                .sku("TEST-001")
                .imageUrl("http://image.url")
                .price(100.00)
                .category("Test Category")
                .build();

        healthcareProductService.createHealthcareProducts(List.of(dto));

        verify(healthcareProductRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testUpdateHealthcareProduct() {
        Long productId = 1L;
        HealthcareProductRequestDTO dto = HealthcareProductRequestDTO.builder()
                .name("Updated Product")
                .sku("TEST-001")
                .build();

        HealthcareProduct existingProduct = new HealthcareProduct();
        existingProduct.setId(productId);

        when(healthcareProductRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        healthcareProductService.updateHealthcareProduct(productId, dto);

        verify(healthcareProductRepository, times(1)).save(any(HealthcareProduct.class));
    }

    @Test
    void testUpdateHealthcareProduct_NotFound() {
        Long productId = 1L;
        HealthcareProductRequestDTO dto = new HealthcareProductRequestDTO();

        when(healthcareProductRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(NoFoundException.class, () -> healthcareProductService.updateHealthcareProduct(productId, dto));
    }

    @Test
    void testDeleteHealthcareProduct() {
        Long productId = 1L;

        healthcareProductService.deleteHealthcareProduct(productId);

        verify(healthcareProductRepository, times(1)).deleteById(productId);
    }

    @Test
    void testGetHealthcareProductById() {
        Long productId = 1L;
        HealthcareProduct healthcareProduct = HealthcareProduct.builder()
                .id(productId)
                .name("Test Product")
                .sku("TEST-001")
                .price(100.00)
                .category("Test Category")
                .build();

        when(healthcareProductRepository.findById(productId)).thenReturn(Optional.of(healthcareProduct));

        HealthcareProductResponseDTO responseDTO = healthcareProductService.getHealtcareProductById(productId);

        assertEquals("Test Product", responseDTO.getName());
        assertEquals("TEST-001", responseDTO.getSku());
        assertEquals(100.00, responseDTO.getPrice());
    }

    @Test
    void testGetHealthcareProductById_NotFound() {
        Long productId = 1L;

        when(healthcareProductRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(NoFoundException.class, () -> healthcareProductService.getHealtcareProductById(productId));
    }

    @Test
    void testGetHealthcareProductPage() {
        Integer pages = 0;
        Integer limit = 5;
        String sortBy = "name";
        String direction = "ASC";
        String productName = "Test";

        HealthcareProduct healthcareProduct = HealthcareProduct.builder()
                .name("Test Product")
                .sku("TEST-001")
                .price(100.00)
                .build();
        Page<HealthcareProduct> page = new PageImpl<>(List.of(healthcareProduct));

        when(healthcareProductRepository.findByNameLikeIgnoreCase(anyString(), any(PageRequest.class))).thenReturn(page);

        ResultPageResponseDTO<HealthcareProductResponseDTO> response = healthcareProductService.getHealthcareProductPage(pages, limit, sortBy, direction, productName);

        assertEquals(1, response.getResult().size());
        assertEquals("Test Product", response.getResult().getFirst().getName());
    }
}
