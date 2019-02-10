package productshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productshop.domein.entities.Product;
import productshop.domein.entities.User;
import productshop.domein.entities.dtos.ProductInRangeDto;
import productshop.domein.entities.dtos.ProductSeedDto;
import productshop.repositoris.ProductsRepository;
import productshop.util.ValidatorUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository,
                              UserService userService,
                              CategoryService categoryService, ModelMapper modelMapper,
                              ValidatorUtil validatorUtil) {
        this.productsRepository = productsRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        for (ProductSeedDto productSeedDto : productSeedDtos) {
            if (!this.validatorUtil.isValid(productSeedDto)){
                this.validatorUtil.violations(productSeedDto)
                        .forEach(vioalation -> System.out.println(vioalation.getMessage()));
                continue;
            }
            Product product = this.modelMapper.map(productSeedDto,Product.class);

            product.setSeller(this.getRandomUser());

            Random random = new Random();

            if (random.nextInt() % 13 != 0){
                product.setBuyer(this.getRandomUser());
            }
            product.setCategories(this.categoryService.getRandomCategory());
            this.productsRepository.saveAndFlush(product);
        }

    }

    @Override
    public List<ProductInRangeDto> productInRange(BigDecimal more, BigDecimal less) {
        List<Product> productEntities = this.productsRepository
                .findAllByPriceBetweenAndBuyerOrderByPrice(more,less,null);
        List<ProductInRangeDto>productInRangeDtos = new ArrayList<>();

        for (Product productEntity : productEntities) {
            ProductInRangeDto productInRangeDto =
                    this.modelMapper.map(productEntity,ProductInRangeDto.class);
           productInRangeDto.setSeller(String.format("%s %s",
                   productEntity.getSeller().getFirstName(),
                   productEntity.getSeller().getLastName()));
            productInRangeDtos.add(productInRangeDto);
        }
        return productInRangeDtos;
    }

    private User getRandomUser(){
        return this.userService.getRandomUserFromBase();
    }
}
