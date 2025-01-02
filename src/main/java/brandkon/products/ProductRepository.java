package brandkon.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBrandId(Long brandId);
    List<Product> findPopularByCategoryId(Long categoryId);
    List<Product> findPopularByBrandId(Long brandId);
}
