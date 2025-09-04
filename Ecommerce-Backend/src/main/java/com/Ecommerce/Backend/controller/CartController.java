package com.Ecommerce.Backend.controller;

import com.Ecommerce.Backend.entity.Cart;
import com.Ecommerce.Backend.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cart")
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartItemService cartItemService;

    @PostMapping("/add")
    public Cart addToCart(@RequestParam Long userId,
                          @RequestParam Long productId,
                          @RequestParam Integer quantity){
        return cartItemService.addToCart(userId,productId,quantity);
    }

    @GetMapping("/{userId}")
    public List<Cart> getCartItem(@PathVariable Long userId){
        return cartItemService.getCartItems(userId);
    }

    @DeleteMapping("/{cartItemId}")
    public String removeFromCart(@PathVariable Long cartItemId){
        cartItemService.removeFromCart(cartItemId);
        return "Item remove from Cart";
    }

    @PutMapping("/{cartItemId}")
    public Cart updateCartItem(@PathVariable Long cartItemId,
                               @RequestParam Integer quantity){
        return cartItemService.updateCartItem(cartItemId,quantity);

    }

}
