package org.tolga.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.tolga.dto.request.AddProductToCartDto;
import org.tolga.dto.request.RemoveProductToCartDto;
import org.tolga.dto.request.UpdateCartRequestDto;
import org.tolga.exception.CartServiceException;
import org.tolga.exception.ErrorType;
import org.tolga.rabbitmq.model.CartModel;
import org.tolga.repository.ICartRepository;
import org.tolga.repository.entity.Cart;
import org.tolga.repository.entity.CartItem;
import org.tolga.utility.ServiceManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService extends ServiceManager<Cart,Long> {
    private final ICartRepository cartRepository;

    public CartService(JpaRepository<Cart, Long> jpaRepository, ICartRepository cartRepository) {
        super(jpaRepository);
        this.cartRepository = cartRepository;
    }
    public Cart getCart(Long id){
        Optional<Cart> optionalCart= findById(id);
        if (optionalCart.isEmpty()){
            throw new CartServiceException(ErrorType.CART_NOT_FOUND);
        }
            return optionalCart.get();
    }
    public String updateCart(UpdateCartRequestDto dto) {
        Optional<Cart> optionalCart = findById(dto.getId());
        if (optionalCart.isEmpty()) {
            throw new CartServiceException(ErrorType.CART_NOT_FOUND);
        }
        Cart existingCart = optionalCart.get();
        existingCart.setCartItems(dto.getCartItems());
        existingCart.setPrice(dto.getPrice());
        update(existingCart);
        return "Successfully updated";

    }
    public String emptyCart(Long id) {
        Optional<Cart> optionalCart = findById(id);
        if (optionalCart.isEmpty()) {
            throw new CartServiceException(ErrorType.CART_NOT_FOUND);
        }

        Cart cartToEmpty = optionalCart.get();
        cartToEmpty.getCartItems().clear();
        cartToEmpty.setPrice(BigDecimal.ZERO);

        save(cartToEmpty);
        return "Your cart is empty";
    }
//customer oluştugu zaman sepet oluşması bir tane sepet olur.
    //optional cart nullsa create. doğrulamak için customerıdye istek atıp customer doğruluk için. openfeign.
//carıtemleri list yerine hashmap tutalım. dto price almasak daha mantıklı.
    public String addProductToCart(AddProductToCartDto dto) {
        Optional<Cart>optionalCart=cartRepository.findByCustomerId(dto.getCustomerId());
        Cart cart = optionalCart.orElseGet(() -> Cart.builder()
                .customerId(dto.getCustomerId())
                .build());
        if (cart.getCartItems() == null) {
            cart.setCartItems(new ArrayList<>());
        }
        CartItem cartItem=CartItem.builder()
                .productName(dto.getProductName())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
        cart.getCartItems().add(cartItem);

        cartRepository.save(cart);
        return "Product added to cart successfully.";
    }

    public void saveCart(CartModel cartModel) {
        Cart cart =Cart.builder()
                .customerId(cartModel.getCustomerId())
                .build();
        save(cart);
    }

    public String removeProductToCart(RemoveProductToCartDto dto) {
        Optional<Cart>optionalCart=cartRepository.findByCustomerId(dto.getCustomerId());
        if (optionalCart.isEmpty()) {
            throw new CartServiceException(ErrorType.CUSTOMER_NOT_FOUND);
        }
        CartItem cartItem=CartItem.builder()
                .productName(dto.getProductName())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
      if ( optionalCart.get().getCartItems().remove(cartItem)){
          return "Product remove from cart successfully";
      }else {
          throw new CartServiceException(ErrorType.PRODUCT_NOT_REMOVED);
      }

    }
}