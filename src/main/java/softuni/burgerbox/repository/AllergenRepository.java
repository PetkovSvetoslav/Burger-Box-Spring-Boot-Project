package softuni.burgerbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.burgerbox.model.entity.AllergenEntity;
import softuni.burgerbox.model.entity.enums.AllergenEnumName;

import java.util.List;
import java.util.Optional;

public interface AllergenRepository extends JpaRepository<AllergenEntity, Long> {

    @Query("select a from AllergenEntity a order by a.name")
    List<AllergenEntity> findAllOrderedByName();


    Optional<AllergenEntity> findByName(AllergenEnumName name);
}
