package softuni.burgerbox.web.employees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.burgerbox.model.entity.CategoryEntity;
import softuni.burgerbox.model.entity.ItemEntity;
import softuni.burgerbox.model.entity.UserEntity;
import softuni.burgerbox.model.entity.enums.RoleEnum;
import softuni.burgerbox.model.entity.enums.TypeEnum;
import softuni.burgerbox.repository.CategoryRepository;
import softuni.burgerbox.repository.ItemRepository;
import softuni.burgerbox.repository.UserRepository;
import softuni.burgerbox.service.CategoryService;
import softuni.burgerbox.service.ItemService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriesTerminalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;

    private UserEntity testUser;

    private CategoryEntity testCategory;

    private ItemEntity testItem;

    @BeforeEach
    void setUp() {
        testUser = initUser();
        userRepository.save(testUser);
        testCategory = categoryRepository.save(new CategoryEntity().setName("testCategory"));
        testItem = itemRepository.save(new ItemEntity().setName("itemFromCategory").setActive(true).
                setCategories(Set.of(testCategory)).setType(TypeEnum.FOOD).setPrice(BigDecimal.TEN));
    }

    @AfterEach
    void tearDown() throws Exception {
        itemService.deleteItem(testItem.getId());
        categoryService.deleteCategory(testCategory.getId());
        userRepository.delete(testUser);
    }

    @Test
    @WithMockUser(value = "testUser", username = "test", roles = "ADMIN")
    void categoriesPageOpens() throws Exception {
        mockMvc
                .perform(get("/terminal/categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("categories-terminal"))
                .andExpect(model().attributeExists("allCategories"));
    }
    @Test
    @WithMockUser(value = "testUser", username = "test", roles = "ADMIN")
    void categoriesByTypeOpens() throws Exception {
        mockMvc
                .perform(get("/terminal/categories/cat/"  + testCategory.getName()))
                .andExpect(status().isOk())
                .andExpect(view().name("foods"))
                .andExpect(model().attributeExists("itemsByType"));
    }

    @Test
    @WithMockUser(value = "testUser", username = "test", roles = "ADMIN")
    void categoriesAddOpens() throws Exception {
        mockMvc
                .perform(get("/terminal/categories/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("category-add"));

    }

    @Test
    @WithMockUser(value = "testUser", username = "test", roles = "ADMIN")
    void testAddCategoryCorrect() throws Exception {
        // how to post MediaType object

//        mockMvc
//                .perform(post("/terminal/categories/add")
//                        .param("name", "desert")
//                        .param("description", "some description")
//                        .param("picture", (String) null)
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//
//                )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/terminal/categories"));
//
//        Optional<CategoryEntity> desert = categoryRepository.findByName("desert");
//        assertTrue(desert.isPresent());
//        assertTrue(desert.get().getId()>0);
//        assertEquals("some description", desert.get().getDescription());
//        categoryRepository.delete(desert.get());
    }





    private UserEntity initUser() {
        return new UserEntity().setUsername("test").setPassword(passwordEncoder.encode("test"))
                .setActive(true).setEmail("test@test.com").setRole(RoleEnum.ADMIN);
    }
}
