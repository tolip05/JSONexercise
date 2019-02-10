package productshop.repositoris;

import org.springframework.data.jpa.repository.JpaRepository;
import productshop.domein.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
