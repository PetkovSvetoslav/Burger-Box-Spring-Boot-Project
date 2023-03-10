package softuni.burgerbox.service;

import softuni.burgerbox.model.binding.ItemAddBindingModel;
import softuni.burgerbox.model.binding.ItemUpdateBindingModel;
import softuni.burgerbox.model.entity.ItemEntity;
import softuni.burgerbox.model.service.ItemServiceModel;
import softuni.burgerbox.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {
    List<ItemViewModel> getAllItems();

    boolean addItem(ItemServiceModel itemServiceModel);

    boolean isItemNameFree(String name);

    List<ItemViewModel> getAllFoods();

    List<ItemViewModel> getAllDrinks();

    List<ItemViewModel> getAllOther();

    List<ItemViewModel> getAllByCategoryName(String categoryName);

    ItemEntity findById(Long itemId);

    void initializeItems();

    ItemUpdateBindingModel getItemUpdateBindingModel(Long id);

    boolean itemUpdate(ItemServiceModel itemServiceModel);

    void deleteItem(Long id) throws Exception;

    List<ItemViewModel> getAllByKeyWord(String keyword);
}
