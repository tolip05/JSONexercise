package productshop.service;


import productshop.domein.entities.dtos.ProductInRangeDto;
import productshop.domein.entities.dtos.ProductSeedDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts(ProductSeedDto[] productSeedDtos);
    List<ProductInRangeDto> productInRange(BigDecimal more,BigDecimal less);
}
