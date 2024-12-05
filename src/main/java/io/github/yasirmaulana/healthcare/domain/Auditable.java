package io.github.yasirmaulana.healthcare.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Auditable {
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime;

    @Column(name = "updated_datetime")
    private LocalDateTime updatedDatetime;

    static final String CURRENT_USER = "System";

    @PrePersist
    protected void onCreate() {
        this.createdDatetime = LocalDateTime.now();
        this.createdBy = getCurrentUser();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDatetime = LocalDateTime.now();
        this.updatedBy = getCurrentUser();
    }

    private String getCurrentUser() {
        return CURRENT_USER;
    }
}
