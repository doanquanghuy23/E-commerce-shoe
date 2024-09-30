package com.huydq.clothing_ecommerce.service;

import com.huydq.clothing_ecommerce.dto.ColorDTO;
import com.huydq.clothing_ecommerce.dto.PageDTO;
import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.entity.Color;

import java.util.List;

public interface ColorService {
    Color convertToEntity(ColorDTO colorDTO);

    ColorDTO convertToDto(Color color);

    List<ColorDTO> getAll();

    PageDTO<List<ColorDTO>> getByPageRequest(PageRequestDTO pageRequestDTO);

    ColorDTO getById(Long id);

    void create(ColorDTO colorDTO);

    void update(ColorDTO colorDTO);

    void delete(Long id);
}
