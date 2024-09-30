package com.huydq.clothing_ecommerce.service;

import com.huydq.clothing_ecommerce.dto.FavouriteDetailDTO;
import com.huydq.clothing_ecommerce.dto.PageDTO;
import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.entity.FavouriteDetail;

import java.util.List;

public interface FavouriteDetailService {
    FavouriteDetail convertToEntity(FavouriteDetailDTO favouriteDetailDTO);

    FavouriteDetailDTO convertToDto(FavouriteDetail favouriteDetail);

    List<FavouriteDetailDTO> getAll();

    PageDTO<List<FavouriteDetailDTO>> getByPageRequest(PageRequestDTO pageRequestDTO);

    FavouriteDetailDTO getById(Long id);

    void create(FavouriteDetailDTO favouriteDetailDTO);

    void update(FavouriteDetailDTO favouriteDetailDTO);

    void delete(Long id);
}
