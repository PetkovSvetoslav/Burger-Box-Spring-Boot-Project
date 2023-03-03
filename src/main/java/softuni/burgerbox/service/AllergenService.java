package softuni.burgerbox.service;

import softuni.burgerbox.model.entity.AllergenEntity;

import java.util.List;

public interface AllergenService {
    void initAllergens();

    List<AllergenEntity> allAllergensOrderedByName();

    AllergenEntity findByName(String name);
}
