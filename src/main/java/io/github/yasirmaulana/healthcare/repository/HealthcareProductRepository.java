package io.github.yasirmaulana.healthcare.repository;

import io.github.yasirmaulana.healthcare.domain.HealthcareProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HealthcareProductRepository extends JpaRepository<HealthcareProduct, Long> {
    Page<HealthcareProduct> findByNameLikeIgnoreCase(String name, Pageable pageable);
}
