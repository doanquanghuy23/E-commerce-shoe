package com.huydq.clothing_ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewProductDTO extends TimeAuditableDTO implements Serializable {
    private Long id;
    private Double rating;
    private String description;
    private ProductDetailDTO productDetail;
    private UserDTO user;
}
