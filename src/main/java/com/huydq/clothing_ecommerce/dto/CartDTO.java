package com.huydq.clothing_ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO implements Serializable {
    private Long id;
    private Long totalProduct;
    private Double totalMoney;
    private UserDTO user;
    private List<CartDetailDTO> cartDetails;

    public CartDTO(Long cartId) {
        this.id = cartId;
    }
}
