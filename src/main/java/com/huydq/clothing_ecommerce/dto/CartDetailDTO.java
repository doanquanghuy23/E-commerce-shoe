package com.huydq.clothing_ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDTO implements Serializable {
    private Long id;
    private Long quantity;
    private ProductDetailDTO productDetail;
    private CartDTO cart;
}
