package brandkon.products;

import brandkon.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getProducts(Long brandId) {
        logger.info("Fetching products for brandId: {}", brandId);
        List<Product> products;
        if (brandId != null) {
            products = productRepository.findByBrandId(brandId);
            if (products.isEmpty()) {
                logger.warn("No products found for brandId: {}", brandId);
            }
        } else {
            products = productRepository.findAll();
        }
        logger.info("Found {} products for brandId: {}", products.size(), brandId);
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getPopularProducts(Long categoryId, Long brandId) {
        logger.info("Fetching popular products for categoryId: {}, brandId: {}", categoryId, brandId);
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<Product> popularProducts;

        if (categoryId != null) {
            popularProducts = productRepository.findPopularByCategoryId(categoryId, pageRequest);
        } else if (brandId != null) {
            popularProducts = productRepository.findPopularByBrandId(brandId, pageRequest);
        } else {
            popularProducts = productRepository.findAllByOrderByPopularityDesc(pageRequest);
        }

        logger.info("Found {} popular products", popularProducts.size());
        return popularProducts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Product getProductByName(String name) {
        return productRepository.findByProductName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with name: " + name));
    }

    public ProductDetailDTO getProductDetail(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
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
                product.getExpirationDays(),
                product.getBrand().getId(),
                product.getBrand().getName()
        );
    }
}
