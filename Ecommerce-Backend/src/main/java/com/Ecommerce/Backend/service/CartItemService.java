package com.Ecommerce.Backend.service;

import com.Ecommerce.Backend.entity.Cart;
import com.Ecommerce.Backend.entity.Product;
import com.Ecommerce.Backend.entity.User;
import com.Ecommerce.Backend.repository.CartItemRepository;
import com.Ecommerce.Backend.repository.ProductRepository;
import com.Ecommerce.Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Cart addToCart(Long userId, Long productId, Integer Quantity){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("Product Not Found"));

        Cart cart = Cart.builder()
                .user(user)
                .product(product)
                .quantity(Quantity)
                .build();

        return cartItemRepository.save(cart);
    }

    public List<Cart> getCartItems(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartItemRepository.findByUser(user);
    }

    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public Cart updateCartItem(Long cartItemId, Integer quantity) {
        Cart cart = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cart.setQuantity(quantity);
        return cartItemRepository.save(cart);
    }
}
