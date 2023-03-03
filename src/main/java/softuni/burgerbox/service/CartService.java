package softuni.burgerbox.service;

import org.springframework.stereotype.Service;
import softuni.burgerbox.model.entity.CartDetailEntity;
import softuni.burgerbox.model.entity.UserEntity;
import softuni.burgerbox.model.view.CartDetailViewModel;
import softuni.burgerbox.service.impl.RestaurantUser;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
  List<CartDetailViewModel> listOfCartDetails(RestaurantUser user);

  Integer addItem(Long itemId, Integer quantityToAdd, UserEntity user);

  BigDecimal updateQuantity(Integer quantity, Long itemId, UserEntity user);

  void removeItem(Long itemId, UserEntity userEntity);

    void emptyCart(Long customerId);

}
