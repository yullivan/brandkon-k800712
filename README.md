## API 명세

1. 카테고리 목록 조회
- URL : `/categories`
- Method : GET
- Body :
  {
  "id": 1,
  "name": "카페",
  "slug": "cafe",
  "imageUrl": "/icons/cafe.png"
  }
2. 브랜드 목록 조회
- URL : `brands?category={category}`
- Method : GET
- Body :
  {
  "id": 1,
  "name": "메가MGC커피",
  "imageUrl": "icons/megacoffee.png"
  }
3. 브랜드 상세 조회
- URL : `/brands/{brandId}`
- Method : GET
- Body :
  {
  "id": 1,
  "name": "메가MGC커피",
  "imageUrl": ""
  }
4. 상품 목록 조회
- URL : `/products` , `/products?brandId={brandId}`
- Method : GET
- Body :
  {
  "id": 1,
  "brandName": "메가MGC커피",
  "productName": "(ICE)아메리카노",
  "price": 2000,
  "imageUrl": ""
  },
5. 인기 상품 목록 조회
- URL : `/products/popular?categoryId={categoryId}` ,
        `/products/popular?brandId={brandId}`
- Method : GET
- Body :
  {
  "id": 1,
  "brandName": "메가MGC커피",
  "productName": "(ICE)아메리카노",
  "price": 2000,
  "imageUrl": ""
  }
6. 상품 상세 조회
- URL : `products/{productId}}`
- Method : GET
- Body :
  {
  "productId": 1,
  "productName": "(ICE)아메리카노",
  "price": 2000,
  "brand": {
  "id": 1,
  "name": "메가MGC커피",
  "guidelines": "..."
  },
  "expirationDays": 366
  }