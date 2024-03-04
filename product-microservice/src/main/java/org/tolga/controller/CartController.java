package org.tolga.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tolga.dto.request.AddProductToCartDto;
import org.tolga.dto.request.RemoveProductToCartDto;
import org.tolga.dto.request.UpdateCartRequestDto;
import org.tolga.repository.entity.Cart;
import org.tolga.service.CartService;



import static org.tolga.constant.ApiUrls.*;

@RestController
@RequestMapping(CART)
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping(GET_CART+"/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id){
        return ResponseEntity.ok(cartService.getCart(id));
    }

    @PatchMapping(UPDATE_CART)
    public ResponseEntity<String> updateCart(@RequestBody UpdateCartRequestDto dto){
        return ResponseEntity.ok(cartService.updateCart(dto));
    }

    @PostMapping(ISEMPTY_CART+"/{id}")
   public ResponseEntity<String> emptyCart(@PathVariable Long id){
       return ResponseEntity.ok(cartService.emptyCart(id));
    }

    @PostMapping(ADD_PRODUCT_TO_CART)
    public  ResponseEntity<String> addProductToCart(@RequestBody AddProductToCartDto dto){
        return ResponseEntity.ok(cartService.addProductToCart(dto));
    }
    @PostMapping(REMOVE_PRODUCT_FROM_CART)
    public  ResponseEntity<String> removeProductToCart(@RequestBody RemoveProductToCartDto dto){
        return ResponseEntity.ok(cartService.removeProductToCart(dto));
    }


}
