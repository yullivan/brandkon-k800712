package brandkon.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
    List<Category> findByNameContaining(String namePattern);
    boolean existsByName(String name);
    List<Category> findAllByOrderByNameAsc();
    List<Category> findByIdIn(List<Long> ids);
    Optional<Category> findByNameIgnoreCase(String name);
    Optional<Category> findBySlug(String slug);
}
