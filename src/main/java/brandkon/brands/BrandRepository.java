package brandkon.brands;

import brandkon.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByCategory(Category category);
    List<Brand> findByCategoryId(Long categoryId);
    List<Brand> findByCategoryName(String categoryName);
}
