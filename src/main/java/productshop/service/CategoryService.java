package productshop.service;

import productshop.domein.entities.Category;
import productshop.domein.entities.dtos.CategorySeedDto;

import java.util.List;

public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDtos);

    List<Category> getRandomCategory();
}
