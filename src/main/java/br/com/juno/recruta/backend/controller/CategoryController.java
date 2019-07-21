package br.com.juno.recruta.backend.controller;

import br.com.juno.recruta.backend.entity.Category;
import br.com.juno.recruta.backend.entity.Product;
import br.com.juno.recruta.backend.service.CategoryService;
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
@RequestMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController extends BaseController<Category> {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        super(categoryService);
        this.categoryService = categoryService;
    }

    /**
     * Given a product {id},
     * list all categories that have been associated to it
     *
     * @param id Product
     * @return List of categories
     */
    @GetMapping("/listAllByProduct/{id}")
    public ResponseEntity<List<Category>> listAllByProduct(@PathVariable Long id) {
        return Optional.of(
                categoryService.findByProduct(id)
        ).filter(
                categories -> categories.isPresent() && !categories.get().isEmpty()
        ).map(
                categories -> ResponseEntity.ok(categories.get())
        ).orElse(
                ResponseEntity.notFound().build()
        );
    }

    /**
     * Given a category {id},
     * list all products that have been associated to it
     *
     * @param id Category
     * @return List of products
     */
    @GetMapping("{id}/listAllProducts")
    public ResponseEntity<List<Product>> listAllProducts(@PathVariable Long id) {
        return Optional.of(
                categoryService.findById(id)
        ).filter(
                category -> category.isPresent() && !category.get().getProducts().isEmpty()
        ).map(
                category -> category.get().getProducts()
        ).map(
                productsFromCategory -> ResponseEntity.ok(productsFromCategory)
        ).orElse(
                ResponseEntity.noContent().build()
        );
    }
}
