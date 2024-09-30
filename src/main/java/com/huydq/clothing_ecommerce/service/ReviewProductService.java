package com.huydq.clothing_ecommerce.service;

import com.huydq.clothing_ecommerce.dto.PageDTO;
import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.dto.ReviewProductDTO;
import com.huydq.clothing_ecommerce.entity.ReviewProduct;

import java.util.List;

public interface ReviewProductService {
    ReviewProduct convertToEntity(ReviewProductDTO reviewProductDTO);

    ReviewProductDTO convertToDto(ReviewProduct reviewProduct);

    List<ReviewProductDTO> getAll();

    PageDTO<List<ReviewProductDTO>> getByPageRequest(PageRequestDTO pageRequestDTO);

    ReviewProductDTO getById(Long id);

    void create(ReviewProductDTO reviewProductDTO);

    void update(ReviewProductDTO reviewProductDTO);

    void delete(Long id);
}
