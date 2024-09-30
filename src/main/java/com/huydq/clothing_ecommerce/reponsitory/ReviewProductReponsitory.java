package com.huydq.clothing_ecommerce.reponsitory;

import com.huydq.clothing_ecommerce.entity.ReviewProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewProductReponsitory extends JpaRepository<ReviewProduct, Long> {
}
