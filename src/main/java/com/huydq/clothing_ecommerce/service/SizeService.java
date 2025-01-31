package com.huydq.clothing_ecommerce.service;

import com.huydq.clothing_ecommerce.dto.PageDTO;
import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.dto.SizeDTO;
import com.huydq.clothing_ecommerce.entity.Size;

import java.util.List;

public interface SizeService {
    Size convertToEntity(SizeDTO sizeDTO);

    SizeDTO convertToDto(Size size);

    List<SizeDTO> getAll();

    PageDTO<List<SizeDTO>> getByPageRequest(PageRequestDTO pageRequestDTO);

    SizeDTO getById(Long id);

    void create(SizeDTO sizeDTO);

    void update(SizeDTO sizeDTO);

    void delete(Long id);
}
