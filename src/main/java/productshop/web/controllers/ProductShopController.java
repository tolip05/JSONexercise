package productshop.web.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import productshop.domein.entities.dtos.CategorySeedDto;
import productshop.domein.entities.dtos.ProductInRangeDto;
import productshop.domein.entities.dtos.ProductSeedDto;
import productshop.domein.entities.dtos.UserSeedDto;
import productshop.service.CategoryService;
import productshop.service.ProductService;
import productshop.service.UserService;
import productshop.util.FileIoUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductShopController implements CommandLineRunner {
    private final String FILE_PATH = "D:\\JavaIvanov\\ProductShop\\src\\main\\resources\\files\\users.json";
    private final String CATEGORY_FILE_PATH =
            "D:\\JavaIvanov\\ProductShop\\src\\main\\resources\\files\\categories.json";
    private final String PRODUCT_FILE_PATH =
            "D:\\JavaIvanov\\ProductShop\\src\\main\\resources\\files\\products.json";


    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final FileIoUtil fileIoUtil;
    private final Gson gson;

    @Autowired
    public ProductShopController(UserService userService,
                                 CategoryService categoryService, ProductService productService, FileIoUtil fileIoUtil, Gson gson) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.fileIoUtil = fileIoUtil;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedUsers();
        this.seedCategory();
        this.seedProduct();
        this.productsInRange();
    }

    private void productsInRange() throws IOException {
        List<ProductInRangeDto>productInRangeDtos =
                this.productService.productInRange(BigDecimal.valueOf(500),BigDecimal.valueOf(1000));
        String productsInRanjeJson = this.gson.toJson(productInRangeDtos);
        this.fileIoUtil.write(productsInRanjeJson,"exportproductsinrange.json");
    }

    private void seedProduct() throws IOException {
        String productContent = this.fileIoUtil.readFile(PRODUCT_FILE_PATH);
        ProductSeedDto[] productSeedDtos =
                this.gson.fromJson(productContent,ProductSeedDto[].class);
        this.productService.seedProducts(productSeedDtos);
    }

    private void seedCategory() throws IOException {
        String categoryContent = this.fileIoUtil.readFile(CATEGORY_FILE_PATH);
        CategorySeedDto[] categorySeedDtos = this.gson.fromJson(categoryContent,CategorySeedDto[].class);
        this.categoryService.seedCategories(categorySeedDtos);
    }

    private void seedUsers() throws IOException {
        String userFileContent = this.fileIoUtil.readFile(FILE_PATH);
        UserSeedDto[] userSeedDtos = this.gson.fromJson(userFileContent, UserSeedDto[].class);
        this.userService.seedUsers(userSeedDtos);
    }
}
