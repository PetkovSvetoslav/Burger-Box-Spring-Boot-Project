package softuni.burgerbox.model.validator;

import softuni.burgerbox.service.ProductService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String> {
    private final ProductService productService;

    public UniqueProductNameValidator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {

        if (name == null){
            return true;
        }
        return productService.isCategoryNameFree(name);

    }
}
