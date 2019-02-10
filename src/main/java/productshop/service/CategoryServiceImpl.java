package productshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productshop.domein.entities.Category;
import productshop.domein.entities.dtos.CategorySeedDto;
import productshop.repositoris.CategoryRepository;
import productshop.util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
        for (CategorySeedDto categorySeedDto : categorySeedDtos) {
            if (!this.validatorUtil.isValid(categorySeedDto)){
              this.validatorUtil.violations(categorySeedDto)
                      .forEach(violation -> System.out.println(violation.getMessage()));
                 continue;
            }
            Category category = this.modelMapper.map(categorySeedDto,Category.class);
            this.categoryRepository.saveAndFlush(category);
        }

    }

    @Override
    public List<Category> getRandomCategory() {

        Random random = new Random();
        List<Category>categories = new ArrayList<>();

        int categoriesCount =
                random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;
        for (int i = 0; i < categoriesCount; i++) {
            categories.add(this.categoryRepository
                    .getOne(random.nextInt((int) (this.categoryRepository.count() - 1)) + 1));
        }
        return categories;
    }
}
