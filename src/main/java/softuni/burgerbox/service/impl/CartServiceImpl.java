package softuni.burgerbox.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.burgerbox.model.entity.CartDetailEntity;
import softuni.burgerbox.model.entity.ItemEntity;
import softuni.burgerbox.model.entity.UserEntity;
import softuni.burgerbox.model.view.CartDetailViewModel;
import softuni.burgerbox.repository.CartDetailRepository;
import softuni.burgerbox.service.CartService;
import softuni.burgerbox.service.ItemService;
import softuni.burgerbox.service.UserService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    private final CartDetailRepository cartDetailRepository;
    private final ItemService itemService;
    private final UserService userService;
    private final ModelMapper modelMapper;


    public CartServiceImpl(CartDetailRepository cartDetailRepository, ItemService itemService, UserService userService, ModelMapper modelMapper) {
        this.cartDetailRepository = cartDetailRepository;

        this.itemService = itemService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CartDetailViewModel> listOfCartDetails(RestaurantUser user) {
        UserEntity userEntity = userService.getUserByLoggedInUser(user);
        List<CartDetailEntity> cartDetailEntities = cartDetailRepository.findByUser(userEntity);
        return cartDetailEntities.stream().map(cartDetailEntity -> modelMapper.map(cartDetailEntity, CartDetailViewModel.class))
                .collect(Collectors.toList());
    }


    public Integer addItem(Long itemId, Integer quantityToAdd, UserEntity user) {
        Integer quantity = quantityToAdd;

        ItemEntity itemEntity = itemService.findById(itemId);

        CartDetailEntity cartDetailEntity = cartDetailRepository.findByUserAndItem(user, itemEntity);

        if (cartDetailEntity != null) {
            quantity = cartDetailEntity.getQuantity() + quantityToAdd;

            cartDetailEntity.setQuantity(quantity);
        } else {
            cartDetailEntity = new CartDetailEntity();
            cartDetailEntity.setUser(user).setItem(itemEntity).setQuantity(quantity);
        }

        cartDetailRepository.save(cartDetailEntity);

        return quantity;

    }

    @Override
    public BigDecimal updateQuantity(Integer quantity, Long itemId, UserEntity user) {
        cartDetailRepository.updateQuantity(quantity, itemId, user.getId());

        ItemEntity item = itemService.findById(itemId);

        return item.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public void removeItem(Long itemId, UserEntity userEntity) {
        cartDetailRepository.deleteByUserAndItem(itemId, userEntity.getId());
    }

    @Override
    public void emptyCart(Long customerId) {
        cartDetailRepository.emptyCart(customerId);
    }
}
