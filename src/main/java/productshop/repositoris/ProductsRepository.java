package productshop.repositoris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productshop.domein.entities.Product;
import productshop.domein.entities.User;
import productshop.domein.entities.dtos.ProductSeedDto;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByPriceBetweenAndBuyerOrderByPrice(BigDecimal more, BigDecimal less, User buyer);
}
