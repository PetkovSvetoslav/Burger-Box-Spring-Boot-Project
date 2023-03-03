package softuni.burgerbox.service;


import softuni.burgerbox.model.entity.UserEntity;
import softuni.burgerbox.model.service.UserRegistrationServiceModel;
import softuni.burgerbox.service.impl.RestaurantUser;

import java.util.List;

public interface UserService {


    boolean registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

    boolean isUserNameFree(Long id, String username);

    void initUsers();

    UserEntity getUserByLoggedInUser(RestaurantUser user);

    List<UserEntity> allUsers();


    void saveUser(UserEntity user, RestaurantUser restaurantUser);

    UserEntity getUserBYId(Long id);

    void deleteUserById(Long id);

    boolean usernameFree(String username);
}
