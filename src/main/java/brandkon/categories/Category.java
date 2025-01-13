package brandkon.categories;

import brandkon.products.Product;
import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String slug;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    public String getCategoryName() {
        switch (this.id.intValue()) {
            case 1: return "cafe";
            case 2: return "gift";
            case 3: return "chicken";
            case 4: return "pizza";
            case 5: return "convenience";
            case 6: return "restaurant";
            case 7: return "bakery";
            default: return "unknown";
        }
    }

    // 카테고리 이름으로 카테고리 ID를 찾는 정적 메서드
    public static Long getIdByName(String name) {
        switch (name.toLowerCase()) {
            case "cafe": return 1L;
            case "gift": return 2L;
            case "chicken": return 3L;
            case "pizza": return 4L;
            case "convenience": return 5L;
            case "restaurant": return 6L;
            case "bakery": return 7L;
            default: return null;
        }
}
}