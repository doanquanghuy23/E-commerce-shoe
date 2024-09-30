package com.huydq.clothing_ecommerce.service;

import com.huydq.clothing_ecommerce.dto.BrandDTO;
import com.huydq.clothing_ecommerce.dto.PageDTO;
import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand convertToEntity(BrandDTO brandDTO);

    BrandDTO convertToDto(Brand brand);

    List<BrandDTO> getAll();

    PageDTO<List<BrandDTO>> getByPageRequest(PageRequestDTO pageRequestDTO);

    BrandDTO getById(Long id);

    void create(BrandDTO brandDTO);

    void update(BrandDTO brandDTO);

    void delete(Long id);
}
