package io.core.app.models;

/**
 * User: franc
 * Date: 09/09/2018
 * Time: 4:14
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@Data
public abstract class AuditModel implements Serializable {
    @Column(name = "created_at", nullable = true, updatable = true)
    @CreatedDate
    private LocalDateTime createdAt;


    @Column(name = "updated_at", nullable = true)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // Getters and Setters (Omitted for brevity)


    public AuditModel() {
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        if (this.createdAt == null)
        {
            this.createdAt = LocalDateTime.now();

        }

    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        if (updatedAt == null)
        {
            this.updatedAt = LocalDateTime.now();

        }

    }


}
