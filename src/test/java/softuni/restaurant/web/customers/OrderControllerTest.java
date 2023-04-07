package softuni.burgerbox.web.customers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.burgerbox.model.entity.UserEntity;
import softuni.burgerbox.model.entity.enums.RoleEnum;
import softuni.burgerbox.repository.ItemRepository;
import softuni.burgerbox.repository.OrderItemRepository;
import softuni.burgerbox.repository.OrderRepository;
import softuni.burgerbox.repository.UserRepository;
import softuni.burgerbox.service.ItemService;
import softuni.burgerbox.service.OrderItemService;
import softuni.burgerbox.service.OrderService;
import softuni.burgerbox.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ModelMapper modelMapper;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        testUser = initUser();
        userRepository.save(testUser);
    }

    private UserEntity initUser() {
        return new UserEntity().setUsername("test").setPassword(passwordEncoder.encode("test"))
                .setActive(true).setEmail("test@test.com").setRole(RoleEnum.CUSTOMER);
    }

    @Test
    @WithMockUser(value = "testUser", username = "test", roles = "CUSTOMER")
    void openOrderPage() throws Exception {
        mockMvc
                .perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(view().name("order"))
                .andExpect(model().attributeExists("order"))
                .andExpect(model().attributeExists("cartDetails"))
                .andExpect(model().attributeExists("total"))
                .andExpect(model().attributeExists("user"));

    }

    @AfterEach
    void tearDown() {
        userRepository.delete(testUser);
    }

    @Test
    void order() {
    }

}
