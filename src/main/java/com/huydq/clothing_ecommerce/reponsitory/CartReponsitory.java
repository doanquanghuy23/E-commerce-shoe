package com.huydq.clothing_ecommerce.reponsitory;

import com.huydq.clothing_ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartReponsitory extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long id);
}
