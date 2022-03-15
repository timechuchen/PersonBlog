package ltd.chuchen.service;

import ltd.chuchen.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategoryList();

    List<Category> getCategoryNameList();

    Integer saveCategory(Category category);

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    Boolean deleteCategoryById(Long id);

    Boolean updateCategory(Category category);
}
