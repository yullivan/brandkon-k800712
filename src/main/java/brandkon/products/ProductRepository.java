package brandkon.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByBrandId(Long brandId);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId ORDER BY p.popularity DESC")
    List<Product> findPopularByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.brand.id = :brandId ORDER BY p.popularity DESC")
    List<Product> findPopularByBrandId(@Param("brandId") Long brandId, Pageable pageable);

    List<Product> findAllByOrderByPopularityDesc(Pageable pageable);

    Optional<Product> findByProductName(String productName);

    List<Product> findByProductNameContaining(String namePattern);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByBrandIdAndCategoryId(Long brandId, Long categoryId);

    List<Product> findAllByOrderByProductNameAsc();

    Page<Product> findByBrandIdOrderByPriceAsc(Long brandId, Pageable pageable);

    boolean existsByProductNameAndBrandId(String productName, Long brandId);

    List<Product> findByCategoryIdOrderByPopularityDesc(Long categoryId, Pageable pageable);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.price >= :price")
    long countProductsWithPriceAtLeast(@Param("price") Double price);
}