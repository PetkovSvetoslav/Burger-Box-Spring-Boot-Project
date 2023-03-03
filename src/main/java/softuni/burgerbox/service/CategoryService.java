package softuni.burgerbox.service;

import softuni.burgerbox.model.entity.CategoryEntity;
import softuni.burgerbox.model.service.CategoryServiceModel;
import softuni.burgerbox.model.view.CategoryEditView;
import softuni.burgerbox.model.view.CategoryViewModel;

import java.util.List;

public interface CategoryService {
    boolean isCategoryNameFree(String name);

    boolean addCategory(CategoryServiceModel serviceModel);

    List<CategoryViewModel> getAllCategories();

    CategoryEditView finById(Long id);

    boolean updateCategory(CategoryServiceModel serviceModel);

    void deleteCategory(Long id);

    List<String> getAllCategoryNames();

    CategoryEntity findCategoryByName(String name);

    void initializeCategories();

    List<CategoryEntity> getAllCategoryEntities();
}
