package com.springboot.jpa.springboot_jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Embeddable
public class Audit {

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Audit() {
    }

    @PrePersist
    public void beforeCreate() {
        createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void beforeUpdate() {
        updateAt = LocalDateTime.now();
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Audit [createAt=" + createAt + ", updateAt=" + updateAt + "]";
    }

}
