package com.huydq.clothing_ecommerce.service;

import com.huydq.clothing_ecommerce.dto.FavouriteDTO;
import com.huydq.clothing_ecommerce.dto.PageDTO;
import com.huydq.clothing_ecommerce.dto.PageRequestDTO;
import com.huydq.clothing_ecommerce.entity.Favourite;

import java.util.List;

public interface FavouriteService {
    Favourite convertToEntity(FavouriteDTO favouriteDTO);

    FavouriteDTO convertToDto(Favourite favourite);

    List<FavouriteDTO> getAll();

    PageDTO<List<FavouriteDTO>> getByPageRequest(PageRequestDTO pageRequestDTO);

    FavouriteDTO getById(Long id);

    void create(FavouriteDTO favouriteDTO);

    void update(FavouriteDTO favouriteDTO);

    void delete(Long id);
}
