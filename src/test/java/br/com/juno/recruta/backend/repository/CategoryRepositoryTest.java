package br.com.juno.recruta.backend.repository;

import br.com.juno.recruta.backend.category.IntegrationTestCategory;
import br.com.juno.recruta.backend.entity.Category;
import br.com.juno.recruta.backend.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@org.junit.experimental.categories.Category(IntegrationTestCategory.class)
public class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository repository;

    @Test
    public void givenCategory_whenFindById_thenIsEquals() {
        Category retail = new Category("Retail");

        entityManager.persistAndFlush(retail);

        assertThat(
                repository.findById(retail.getId()).get()
        ).isEqualTo(retail);
    }

    @Test
    public void givenCategory_whenFindByProduct_thenCategoryIsEquals() {
        Product product = new Product("TDD with Java");
        Category books = new Category("Books");

        books.setProducts(
                Collections.singletonList(product)
        );

        product.setCategories(
                Collections.singletonList(books)
        );

        entityManager.persist(product);
        entityManager.persist(books);
        entityManager.flush();

        assertThat(
                repository.findByProduct(product.getId()).get().get(0)
        ).isEqualTo(books);
    }

    @Test
    public void givenCategories_whenFindAll_thenIsEquals() {
        List<Category> categories = Arrays.asList(
                new Category("Samples"),
                new Category("Proof of concept")
        );

        categories.forEach(entityManager::persist);
        entityManager.flush();

        assertThat(
                repository.findAll().get()
        )
                .contains(categories.get(0))
                .contains(categories.get(1));
    }

    @Test
    public void givenCategories_whenFindWithPattern_thenCategoryIsEquals() {
        String pattern = "5";

        Category fromFifteenToThirtyOff = new Category("From 15-30% OFF");
        Category fromFifteenToThirtyFiveOff = new Category("From 15-35% OFF");

        entityManager.persist(fromFifteenToThirtyOff);
        entityManager.persist(fromFifteenToThirtyFiveOff);
        entityManager.flush();

        assertThat(
                repository.findWithPattern(pattern).get()
        ).isEqualTo(fromFifteenToThirtyFiveOff);
    }
}