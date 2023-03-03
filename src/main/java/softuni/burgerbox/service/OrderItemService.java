package softuni.burgerbox.service;

import softuni.burgerbox.model.entity.OrderItemEntity;

import java.util.Set;

public interface OrderItemService {
    void initializeOrderItems();

    OrderItemEntity getOrderItemById(Long id);

    void save(Set<OrderItemEntity> orderItemEntities);
}
