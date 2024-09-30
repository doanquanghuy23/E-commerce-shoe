package com.huydq.clothing_ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDetailDTO implements Serializable {
    private Long id;
    private ProductDetailDTO productDetail;
    private Double price;
    private Long quantity;
    private Double totalPrice;
    private BillDTO bill;
}
