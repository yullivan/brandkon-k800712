package brandkon.brands;

import brandkon.categories.Category;
import jakarta.persistence.*;

@Entity
public class Brand {
    public Brand() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String guidelines;


    public Brand(String name, String imageUrl, Category category, String guidelines) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.guidelines = guidelines;
    }

    public String getGuidelines() {
        return guidelines;
    }

    public void setGuidelines(String guidelines) {
        this.guidelines = guidelines;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
