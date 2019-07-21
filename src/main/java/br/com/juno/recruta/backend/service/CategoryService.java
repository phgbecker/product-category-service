package br.com.juno.recruta.backend.service;

import br.com.juno.recruta.backend.repository.CategoryRepository;
import br.com.juno.recruta.backend.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService extends BaseService<Category> {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        super(repository);
        this.categoryRepository = repository;
    }

    public Optional<List<Category>> findByProduct(Long id) {
        return categoryRepository.findByProduct(id);
    }
}
