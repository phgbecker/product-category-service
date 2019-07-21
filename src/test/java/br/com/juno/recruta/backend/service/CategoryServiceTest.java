package br.com.juno.recruta.backend.service;

import br.com.juno.recruta.backend.category.UnitTestCategory;
import br.com.juno.recruta.backend.entity.Category;
import br.com.juno.recruta.backend.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@org.junit.experimental.categories.Category(UnitTestCategory.class)
public class CategoryServiceTest {
    private Category category;
    private Category expectedCategory;

    @MockBean
    private CategoryService service;

    @Before
    public void setUp() {
        category = new Category();
        category.setName("Retail");
        category.setProducts(
                Collections.singletonList(
                        new Product("TDD with Java")
                )
        );

        expectedCategory = new Category();
        expectedCategory.setName("Retail");
        expectedCategory.setProducts(
                Collections.singletonList(
                        new Product("TDD with Java")
                )
        );
    }

    @Test
    public void givenCategory_whenFindById_thenIsEquals() {
        when(
                service.findById(category.getId())
        ).thenReturn(
                Optional.of(category)
        );

        Assertions.assertThat(
                service.findById(category.getId()).get()
        ).isEqualTo(expectedCategory);
    }

    @Test
    public void givenCategory_whenFindByProduct_thenCategoryIsEquals() {
        when(
                service.findByProduct(category.getProducts().get(0).getId())
        ).thenReturn(
                Optional.of(Collections.singletonList(category))
        );

        Product product = new Product("TDD with Java");

        Assertions.assertThat(
                service.findByProduct(product.getId()).get()
        ).isEqualTo(
                Collections.singletonList(expectedCategory)
        );
    }

    @Test
    public void givenCategory_whenFindAll_thenIsEquals() {
        when(
                service.findAll()
        ).thenReturn(
                Optional.of(Collections.singletonList(category))
        );

        Assertions.assertThat(
                service.findAll().get()
        ).isEqualTo(
                Collections.singletonList(expectedCategory)
        );
    }

    @Test
    public void givenCategory_whenFindWithPattern_thenCategoryIsEquals() {
        String pattern = "r";

        when(
                service.findWithPattern(pattern)
        ).thenReturn(
                Optional.of(category)
        );

        Assertions.assertThat(
                service.findWithPattern(pattern).get()
        ).isEqualTo(expectedCategory);
    }
}