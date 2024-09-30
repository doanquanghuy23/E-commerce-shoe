package com.huydq.clothing_ecommerce.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class TimeAuditableDTO {
    private Date createdAt;
    private Date updatedAt;
}
