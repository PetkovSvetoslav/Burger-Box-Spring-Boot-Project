package softuni.burgerbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.burgerbox.model.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
