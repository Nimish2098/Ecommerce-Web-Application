package com.Ecommerce.Backend.repository;

import com.Ecommerce.Backend.entity.Cart;
import com.Ecommerce.Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CartItemRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByUser(User user);
}
