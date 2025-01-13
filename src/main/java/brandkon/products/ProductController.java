package brandkon.products;

import brandkon.ErrorResponse;
import brandkon.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam(required = false) Long brandId) {
        List<ProductDTO> products = productService.getProducts(brandId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<ProductDTO>> getPopularProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long brandId) {
        List<ProductDTO> popularProducts = productService.getPopularProducts(categoryId, brandId);
        if (popularProducts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(popularProducts);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDTO> getProductDetail(@PathVariable Long productId) {
        ProductDetailDTO product = productService.getProductDetail(productId);
        return ResponseEntity.ok(product);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("Not Found", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}