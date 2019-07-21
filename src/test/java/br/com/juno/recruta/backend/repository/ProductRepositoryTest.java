package br.com.juno.recruta.backend.repository;

import br.com.juno.recruta.backend.entity.Product;
import br.com.juno.recruta.backend.category.IntegrationTestCategory;
import br.com.juno.recruta.backend.entity.Category;
import org.assertj.core.api.Assertions;
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
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    @Test
    public void givenProduct_whenFindById_thenIsEquals() {
        Product product = new Product("Purse");

        entityManager.persistAndFlush(product);

        Assertions.assertThat(
                repository.findById(product.getId()).get()
        ).isEqualTo(product);
    }

    @Test
    public void givenProduct_whenFindByCategory_thenProductIsEquals() {
        Category category = new Category("Sports Apparel");
        Product soccerShoe = new Product("Soccer shoe");

        category.setProducts(
                Collections.singletonList(soccerShoe)
        );

        soccerShoe.setCategories(
                Collections.singletonList(category)
        );

        entityManager.persist(category);
        entityManager.persist(soccerShoe);
        entityManager.flush();

        Assertions.assertThat(
                repository.findByCategory(category.getId()).get().get(0)
        ).isEqualTo(soccerShoe);
    }

    @Test
    public void givenProducts_whenFindAll_thenIsEquals() {
        List<Product> products = Arrays.asList(
                new Product("Spaghetti"),
                new Product("Hamburger")
        );

        products.forEach(entityManager::persist);
        entityManager.flush();

        Assertions.assertThat(
                repository.findAll().get()
        )
                .contains(products.get(0))
                .contains(products.get(1));
    }

    @Test
    public void givenProducts_whenFindWithPattern_thenProductIsEquals() {
        String pattern = "x";

        Product radioRev1 = new Product("Radio TXX-J rev A1");
        Product radioRev2 = new Product("Radio TXX-J rev X2");

        entityManager.persist(radioRev1);
        entityManager.persist(radioRev2);
        entityManager.flush();

        Assertions.assertThat(
                repository.findWithPattern(pattern).get()
        ).isEqualTo(radioRev2);
    }
}