package brandkon.products;

public record ProductDetailDTO(
        Long id,
        String productName,
        int price,
        int expirationDays,
        Long brandId,
        String brandName
) {}
