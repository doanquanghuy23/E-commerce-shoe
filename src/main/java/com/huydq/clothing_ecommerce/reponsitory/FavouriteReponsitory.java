package com.huydq.clothing_ecommerce.reponsitory;

import com.huydq.clothing_ecommerce.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteReponsitory extends JpaRepository<Favourite, Long> {
}
