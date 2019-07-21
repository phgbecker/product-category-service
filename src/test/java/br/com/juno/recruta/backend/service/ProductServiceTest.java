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
public class ProductServiceTest {
    private Product product;
    private Product expectedProduct;

    @MockBean
    private ProductService service;

    @Before
    public void setUp() {
        product = new Product();
        product.setName("Purse");
        product.setCategories(
                Collections.singletonList(
                        new Category("Women")
                )
        );

        expectedProduct = new Product();
        expectedProduct.setName("Purse");
        expectedProduct.setCategories(
                Collections.singletonList(
                        new Category("Women")
                )
        );
    }

    @Test
    public void givenProduct_whenFindById_thenIsEquals() {
        when(
                service.findById(product.getId())
        ).thenReturn(
                Optional.of(product)
        );

        Assertions.assertThat(
                service.findById(product.getId()).get()
        ).isEqualTo(expectedProduct);
    }

    @Test
    public void givenProduct_whenFindByProduct_thenProductIsEquals() {
        when(
                service.findByCategory(product.getCategories().get(0).getId())
        ).thenReturn(
                Optional.of(Collections.singletonList(product))
        );

        Product product = new Product("TDD with Java");

        Assertions.assertThat(
                service.findByCategory(product.getId()).get()
        ).isEqualTo(
                Collections.singletonList(expectedProduct)
        );
    }

    @Test
    public void givenProduct_whenFindAll_thenIsEquals() {
        when(
                service.findAll()
        ).thenReturn(
                Optional.of(Collections.singletonList(product))
        );

        Assertions.assertThat(
                service.findAll().get()
        ).isEqualTo(
                Collections.singletonList(expectedProduct)
        );
    }

    @Test
    public void givenProduct_whenFindWithPattern_thenProductIsEquals() {
        String pattern = "p";

        when(
                service.findWithPattern(pattern)
        ).thenReturn(
                Optional.of(product)
        );

        Assertions.assertThat(
                service.findWithPattern(pattern).get()
        ).isEqualTo(expectedProduct);
    }
}