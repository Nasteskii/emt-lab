package mk.ukim.finki.emt.library.web.rest;

import mk.ukim.finki.emt.library.model.enumerations.Category;
import mk.ukim.finki.emt.library.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategoriesPage() {
        return this.categoryService.findAll();
    }

}
