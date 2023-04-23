package mk.ukim.finki.emt.library.service.impl;

import mk.ukim.finki.emt.library.model.enumerations.Category;
import mk.ukim.finki.emt.library.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    public List<Category> findAll() {
        return Arrays.stream(Category.values()).toList();
    }
}
