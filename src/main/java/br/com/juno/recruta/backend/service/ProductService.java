package br.com.juno.recruta.backend.service;

import br.com.juno.recruta.backend.repository.ProductRepository;
import br.com.juno.recruta.backend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService extends BaseService<Product> {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository repository) {
        super(repository);
        this.productRepository = repository;
    }

    public Optional<List<Product>> findByCategory(Long id) {
        return productRepository.findByCategory(id);
    }
}
