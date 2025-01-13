package brandkon.products;

import brandkon.brands.Brand;
import brandkon.categories.Category;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int price;
    private String imageUrl;
    private int expirationDays;
    private int popularity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Product() {}

    public Product(Long id, String productName, int price, int expirationDays, Brand brand) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.expirationDays = expirationDays;
        this.brand = brand;

    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getExpirationDays() { return expirationDays; }
    public void setExpirationDays(int expirationDays) { this.expirationDays = expirationDays; }

    public int getPopularity() { return popularity; }
    public void setPopularity(int popularity) { this.popularity = popularity; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", expirationDays=" + expirationDays +
                ", popularity=" + popularity +
                ", category=" + category +
                ", brand=" + brand +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && expirationDays == product.expirationDays && popularity == product.popularity && Objects.equals(id, product.id) && Objects.equals(productName, product.productName) && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(category, product.category) && Objects.equals(brand, product.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price, imageUrl, expirationDays, popularity, category, brand);
    }
}


