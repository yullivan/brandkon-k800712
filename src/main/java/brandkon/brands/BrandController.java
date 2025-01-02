package brandkon.brands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandDTO>> getBrandsByCategory(@RequestParam(required = false) String category) {
        return ResponseEntity.ok(brandService.getBrandsByCategory(category));
    }

//    @GetMapping("/{brandId}")
//    public ResponseEntity<BrandDetailDTO> getBrandById(@PathVariable Long brandId) {
//        return ResponseEntity.ok(brandService.getBrandById(brandId));
//    }
}
