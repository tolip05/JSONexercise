package productshop.repositoris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productshop.domein.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
