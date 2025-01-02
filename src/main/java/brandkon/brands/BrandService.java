package brandkon.brands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<BrandDTO> getBrandsByCategory(String category) {
        List<Brand> brands = (category != null) ?
                brandRepository.findByCategory(category) :
                brandRepository.findAll();
        return brands.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    public BrandDetailDTO getBrandById(Long brandId) {
//        Brand brand = brandRepository.findById(brandId)
//                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
//        return convertToDetailDTO(brand);
//    }

    private BrandDTO convertToDTO(Brand brand) {
        return new BrandDTO(
                brand.getId(),
                brand.getName(),
                brand.getImageUrl()
        );
    }

    private BrandDetailDTO convertToDetailDTO(Brand brand) {
        return new BrandDetailDTO(
                brand.getId(),
                brand.getName(),
                brand.getImageUrl(),
                brand.getGuidelines()
        );
    }
}
