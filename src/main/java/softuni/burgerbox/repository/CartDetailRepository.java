package softuni.burgerbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import softuni.burgerbox.model.entity.CartDetailEntity;
import softuni.burgerbox.model.entity.ItemEntity;
import softuni.burgerbox.model.entity.UserEntity;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetailEntity, Long> {
    List<CartDetailEntity> findByUser(UserEntity userEntity);

    CartDetailEntity findByUserAndItem(UserEntity user, ItemEntity item);

    @Query("UPDATE CartDetailEntity c SET c.quantity = ?1 where c.item.id = ?2 and c.user.id = ?3")
    @Modifying
    void updateQuantity(Integer quantity, Long itemId, Long userId);

    @Query("DELETE  from CartDetailEntity c where c.user.id=?2 and c.item.id = ?1")
    @Modifying
    void deleteByUserAndItem(Long itemId, Long userId);

    @Query("delete from CartDetailEntity c where c.user.id =?1")
    @Modifying
    void emptyCart(Long customerId);
}
