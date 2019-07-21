package br.com.juno.recruta.backend.controller;

import br.com.juno.recruta.backend.entity.Category;
import br.com.juno.recruta.backend.entity.Product;
import br.com.juno.recruta.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController extends BaseController<Product> {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
    }

    /**
     * Given a category {id},
     * list all products that have been associated to it
     *
     * @param id Category
     * @return List of products
     */
    @GetMapping(value = "/listAllByCategory/{id}")
    public ResponseEntity<List<Product>> listAllByCategory(@PathVariable Long id) {
        return Optional.of(
                productService.findByCategory(id)
        ).filter(
                products -> products.isPresent() && !products.get().isEmpty()
        ).map(
                products -> ResponseEntity.ok(products.get())
        ).orElse(
                ResponseEntity.noContent().build()
        );
    }

    /**
     * Given a product {id},
     * list all categories that have been associated to it
     *
     * @param id Product
     * @return List of categories
     */
    @GetMapping("{id}/listAllCategories")
    public ResponseEntity<List<Category>> listAllCategories(@PathVariable Long id) {
        return Optional.of(
                productService.findById(id)
        ).filter(
                product -> product.isPresent() && !product.get().getCategories().isEmpty()
        ).map(
                product -> product.get().getCategories()
        ).map(
                categoriesFromProduct -> ResponseEntity.ok(categoriesFromProduct)
        ).orElse(
                ResponseEntity.noContent().build()
        );
    }
}
