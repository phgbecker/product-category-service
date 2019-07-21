package br.com.juno.recruta.backend.repository;

import br.com.juno.recruta.backend.entity.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends BaseRepository<Category, Long> {

    @Query(value = "SELECT c FROM Category AS c INNER JOIN c.products AS p WHERE p.id = :id ORDER BY c.name ASC")
    Optional<List<Category>> findByProduct(Long id);
}
