package com.huydq.clothing_ecommerce.service;

import com.huydq.clothing_ecommerce.dto.CartDTO;
import com.huydq.clothing_ecommerce.dto.PageDTO;
import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.entity.Cart;

import java.util.List;

public interface CartService {
    Cart convertToEntity(CartDTO cartDTO);

    CartDTO convertToDto(Cart cart);

    List<CartDTO> getAll();

    CartDTO getByUserId(Long id);

    PageDTO<List<CartDTO>> getByPageRequest(PageRequestDTO pageRequestDTO);

    CartDTO getById(Long id);

    void create(CartDTO cartDTO);

    void update(CartDTO cartDTO);

    void delete(Long id);
}
