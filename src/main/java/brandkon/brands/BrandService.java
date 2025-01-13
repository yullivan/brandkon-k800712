package brandkon.brands;

import brandkon.ResourceNotFoundException;
import brandkon.categories.Category;
import brandkon.categories.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BrandService {

    private static final Logger logger = LoggerFactory.getLogger(BrandService.class);
    private final BrandRepository brandRepository;
    private final CategoryService categoryService;


    public BrandService(BrandRepository brandRepository, CategoryService categoryService) {
        this.brandRepository = brandRepository;
        this.categoryService = categoryService;
    }

    public List<BrandDTO> getBrandsByCategoryId(Long categoryId) {
        List<Brand> brands = brandRepository.findByCategoryId(categoryId);
        return brands.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BrandDTO> getBrandsByCategoryName(String categoryName) {
        logger.info("Fetching brands for category: {}", categoryName);
        Category category = categoryService.getCategoryByName(categoryName);
        List<Brand> brands = brandRepository.findByCategory(category);
        if (brands.isEmpty()) {
            logger.warn("No brands found for category: {}", categoryName);
            throw new ResourceNotFoundException("No brands found for category: " + categoryName);
        }
        logger.info("Found {} brands for category: {}", brands.size(), categoryName);
        return brands.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public BrandDetailDTO getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + id));
        return convertToDetailDTO(brand);
    }

    public BrandDTO createBrand(BrandDTO brandDTO) {
        Brand brand = new Brand(brandDTO.name(), brandDTO.imageUrl(), null, null);
        Brand savedBrand = brandRepository.save(brand);
        return convertToDTO(savedBrand);
    }

    public List<BrandDTO> getBrandsByCategorySlug(String categorySlug) {
        Category category = categoryService.getCategoryBySlug(categorySlug);
        List<Brand> brands = brandRepository.findByCategory(category);
        return brands.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + id));
        brand.setName(brandDTO.name());
        brand.setImageUrl(brandDTO.imageUrl());
        Brand updatedBrand = brandRepository.save(brand);
        return convertToDTO(updatedBrand);
    }

    private BrandDTO convertToDTO(Brand brand) {
        return new BrandDTO(
                brand.getId(),
                brand.getName(),
                brand.getImageUrl(),
                brand.getCategory() != null ? brand.getCategory().getId() : null,
                brand.getGuidelines()
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
