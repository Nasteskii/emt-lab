package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.enumerations.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
}
