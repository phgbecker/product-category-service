package br.com.juno.recruta.backend.entity;

import br.com.juno.recruta.backend.category.UnitTestCategory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@org.junit.experimental.categories.Category(UnitTestCategory.class)
public class CategoryTest {
    private Category category;

    @Before
    public void setUp() {
        category = new Category("Newspaper");
    }

    @Test
    public void givenCategory_whenNewInstance_thenIsEquals() {
        assertThat(category).isEqualTo(new Category("Newspaper"));
    }

    @Test
    public void givenCategory_whenGetName_thenIsEquals() {
        assertThat(category.getName()).isEqualTo("Newspaper");
    }

    @Test
    public void givenCategory_whenGetProducts_thenIsEmpty() {
        category.setProducts(Collections.emptyList());
        assertThat(category.getProducts()).isEmpty();
    }

    @Test
    public void givenCategory_whenGetProducts_thenIsEquals() {
        category.setProducts(
                Collections.singletonList(
                        new Product("The Washington Post")
                )
        );

        assertThat(category.getProducts())
                .isEqualTo(
                        Collections.singletonList(
                                new Product("The Washington Post")
                        )
                );
    }

    @Test
    public void givenCategory_whenToString_thenIsEquals() {
        assertThat(category.toString()).isEqualTo("Category(products=null)");
    }
}