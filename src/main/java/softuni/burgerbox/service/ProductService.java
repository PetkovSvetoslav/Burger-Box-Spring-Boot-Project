package softuni.burgerbox.service;

import softuni.burgerbox.model.entity.ProductEntity;
import softuni.burgerbox.model.service.ProductServiceModel;
import softuni.burgerbox.model.view.ProductEditView;
import softuni.burgerbox.model.view.ProductViewModel;

import java.util.List;

public interface ProductService {
    List<ProductViewModel> allProducts();

    boolean addProduct(ProductServiceModel productServiceModel);

    ProductEditView findById(Long id);

    boolean isCategoryNameFree(String name);

    List<String> allProductsByName();

    ProductEntity findProductByName(String name);

    void initializeProducts();

    List<ProductEntity> getAllProductEntities();

    void deleteProduct(Long id);
}
