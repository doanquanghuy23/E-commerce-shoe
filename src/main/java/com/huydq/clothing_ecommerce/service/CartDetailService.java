package com.huydq.clothing_ecommerce.service;

import com.huydq.clothing_ecommerce.dto.CartDTO;
import com.huydq.clothing_ecommerce.dto.CartDetailDTO;
import com.huydq.clothing_ecommerce.dto.PageDTO;
import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.entity.CartDetail;

import java.util.List;

public interface CartDetailService {
    CartDetail convertToEntity(CartDetailDTO cartDetailDTO);

    CartDetailDTO convertToDto(CartDetail cartDetail);

    List<CartDetailDTO> getAll();

    PageDTO<List<CartDetailDTO>> getByPageRequest(PageRequestDTO pageRequestDTO);

    CartDetailDTO getById(Long id);

    CartDetailDTO create(CartDetailDTO cartDetailDTO);

    CartDetailDTO update(CartDetailDTO cartDetailDTO);

    void delete(Long id);

    CartDetailDTO findByCartIdAndProductDetailId(Long cartId, Long productDetailId);
}
