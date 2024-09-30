package com.huydq.clothing_ecommerce.reponsitory;

import com.huydq.clothing_ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryReponsitory extends JpaRepository<Category, Long> {
}
