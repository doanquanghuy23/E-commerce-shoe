package com.huydq.clothing_ecommerce.service;

import com.huydq.clothing_ecommerce.dto.PageDTO;
import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.dto.PromotionDetailDTO;
import com.huydq.clothing_ecommerce.entity.PromotionDetail;

import java.util.List;

public interface PromotionDetailService {
    PromotionDetail convertToEntity(PromotionDetailDTO promotionDetailDTO);

    PromotionDetailDTO convertToDto(PromotionDetail promotionDetail);

    List<PromotionDetailDTO> getAll();

    PageDTO<List<PromotionDetailDTO>> getByPageRequest(PageRequestDTO pageRequestDTO);

    PromotionDetailDTO getById(Long id);

    void create(PromotionDetailDTO promotionDetailDTO);

    void update(PromotionDetailDTO promotionDetailDTO);

    void delete(Long id);
}
