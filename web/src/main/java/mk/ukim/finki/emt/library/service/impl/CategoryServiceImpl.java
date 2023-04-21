package mk.ukim.finki.emt.library.service.impl;

import mk.ukim.finki.emt.library.model.enumerations.Category;
import mk.ukim.finki.emt.library.repository.CategoryRepository;
import mk.ukim.finki.emt.library.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
