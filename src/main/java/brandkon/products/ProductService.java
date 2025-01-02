package brandkon.products;

import brandkon.ResourceNotFoundException;
import brandkon.brands.BrandDTO;
import brandkon.brands.BrandDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getProducts(Long brandId) {
        List<Product> products = (brandId != null) ?
                productRepository.findByBrandId(brandId) :
                productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getPopularProducts(Long categoryId, Long brandId) {
        List<Product> popularProducts;

        if (categoryId != null) {

            popularProducts = productRepository.findPopularByCategoryId(categoryId);
        } else if (brandId != null) {

            popularProducts = productRepository.findPopularByBrandId(brandId);
        } else {

            popularProducts = new ArrayList<>(); // 빈 리스트 반환 또는 다른 로직 추가
        }

        return popularProducts.stream()
                .limit(5)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    public ProductDetailDTO getProductDetail(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return convertToDetailDTO(product);
    }

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    private ProductDetailDTO convertToDetailDTO(Product product) {
        return new ProductDetailDTO(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                new BrandDTO(product.getBrand().getId(),
                        product.getBrand().getName(),
                        product.getBrand().getImageUrl()),
                product.getExpirationDays()
        );
    }
}

