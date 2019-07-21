package br.com.juno.recruta.backend.entity;

import br.com.juno.recruta.backend.category.UnitTestCategory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@org.junit.experimental.categories.Category(UnitTestCategory.class)
public class ProductTest {
    private Product product;

    @Before
    public void setUp() {
        product = new Product("The Washington Post");
    }

    @Test
    public void givenProduct_whenNewInstance_thenIsEquals() {
        assertThat(product).isEqualTo(new Product("The Washington Post"));
    }

    @Test
    public void givenProduct_whenGetName_thenIsEquals() {
        assertThat(product.getName()).isEqualTo("The Washington Post");
    }

    @Test
    public void givenProduct_whenGetCategories_thenIsEmpty() {
        product.setCategories(Collections.emptyList());
        assertThat(product.getCategories()).isEmpty();
    }

    @Test
    public void givenProduct_whenGetCategories_thenIsEquals() {
        product.setCategories(
                Collections.singletonList(
                        new Category("Newspaper")
                )
        );

        assertThat(product.getCategories())
                .isEqualTo(
                        Collections.singletonList(
                                new Category("Newspaper")
                        )
                );
    }

    @Test
    public void givenProduct_whenToString_thenIsEquals() {
        assertThat(product.toString()).isEqualTo("Product(categories=null)");
    }
}