package brandkon.brands;

import brandkon.ErrorResponse;
import brandkon.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {


    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<BrandDTO>> getBrandsByCategoryId(@PathVariable Long categoryId) {
        List<BrandDTO> brands = brandService.getBrandsByCategoryId(categoryId);
        return ResponseEntity.ok(brands);
    }

    @GetMapping
    public ResponseEntity<List<BrandDTO>> getBrandsByCategory(@RequestParam String category) {
        List<BrandDTO> brands = brandService.getBrandsByCategorySlug(category);
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDetailDTO> getBrandById(@PathVariable Long id) {
        BrandDetailDTO brand = brandService.getBrandById(id);
        return ResponseEntity.ok(brand);
    }

    @PostMapping
    public ResponseEntity<BrandDTO> createBrand(@RequestBody BrandDTO brandDTO) {
        BrandDTO createdBrand = brandService.createBrand(brandDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBrand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        BrandDTO updatedBrand = brandService.updateBrand(id, brandDTO);
        return ResponseEntity.ok(updatedBrand);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("Not Found", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

