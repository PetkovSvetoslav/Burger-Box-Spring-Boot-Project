package softuni.burgerbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.burgerbox.model.entity.PictureEntity;

public interface PictureRepository extends JpaRepository<PictureEntity, Long> {
}
