package br.com.juno.recruta.backend.repository;

import br.com.juno.recruta.backend.entity.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends BaseRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product AS p INNER JOIN p.categories AS c WHERE c.id = :id ORDER BY p.name ASC")
    Optional<List<Product>> findByCategory(Long id);
}
