package brandkon.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam(required = false) Long brandId) {
        return ResponseEntity.ok(productService.getProducts(brandId));
    }

    @GetMapping("/popular")
    public ResponseEntity<List<ProductDTO>> getPopularProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long brandId) {
        return ResponseEntity.ok(productService.getPopularProducts(categoryId, brandId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDTO> getProductDetail(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductDetail(productId));
    }
}
