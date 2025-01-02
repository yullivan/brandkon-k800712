package brandkon.products;

import brandkon.brands.BrandDTO;

public record ProductDetailDTO(Long productId,
                               String productName,
                               int price,
                               BrandDTO brand,
                               int expirationDays) {
}
