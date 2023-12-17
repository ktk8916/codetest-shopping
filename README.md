# 구성
- Java 17
- Spring Boot 3.x.x
- MySQL
- Mybatis

# ERD

# 배포
http://35.232.189.24:8080

# API 명세
간단하게 확인  
http://35.232.189.24:8080/swagger-ui/index.html#/

## Auth

<details>
  <summary><h3>회원가입</h3></summary>


```bash
curl -X POST http://35.232.189.24:8080/api/v1/auth/signup \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "username",
    "password": "password",
    "nickname": "nickname"
  }'
```

**헤더 파라미터:**

| 파라미터      | 타입    | 필수여부 | 설명               |
|--------------|--------|----------|--------------------|
| Content-Type | String | 필수     | application/json  |

**바디 파라미터:**

| 파라미터  | 타입   | 필수여부 | 설명     |
|----------|--------|----------|----------|
| username | String | 필수     | 사용자 ID |
| password | String | 필수     | 비밀번호  |
| nickname | String | 필수     | 닉네임    |

**응답:**

```json
201
{
  "accessToken": "string"
}
```

- `accessToken`: 액세스 토큰

</details>


<details>
  <summary><h3>로그인</h3></summary>

```bash
curl -X POST http://35.232.189.24:8080/api/v1/auth/login \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "username",
    "password": "password"
  }'
```

**헤더 파라미터:**

| 파라미터      | 타입    | 필수여부 | 설명               |
|--------------|--------|----------|--------------------|
| Content-Type | String | 필수     | application/json  |

**바디 파라미터:**

| 파라미터  | 타입   | 필수여부 | 설명     |
|----------|--------|----------|----------|
| username | String | 필수     | 사용자 ID |
| password | String | 필수     | 비밀번호  |

**응답:**

```json
200
{
  "accessToken": "string"
}
```
**응답 필드:**

- `accessToken`: 액세스 토큰


</details>

## Product


<details>
  <summary><h3>상품 등록</h3></summary>

```bash
curl -X POST http://35.232.189.24:8080/api/v1/product \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer {token}' \
  -d '{
    "name": "상품 이름",
    "price": 100
  }'

```
**헤더 파라미터:**

| 파라미터      | 타입    | 필수여부 | 설명               |
|--------------|--------|----------|--------------------|
| Content-Type | String | 필수     | application/json  |
| Authorization | String | 필수     | Bearer 토큰 (사용자 인증용)  |

**바디 파라미터:**

| 파라미터  | 타입   | 필수여부 | 설명     |
|----------|--------|----------|----------|
| name | String | 필수     | 상품이름 |
| price | Number | 필수     | 가격  |

**응답:**

```json
201
```
</details>


<details>
  <summary><h3>상품 검색</h3></summary>

```bash
curl -X GET http://35.232.189.24:8080/api/v1/product \
  -H 'Content-Type: application/json' \
  -d '{
    "keyword": "keyword",
    "page": 0,
    "size": 5
  }'
```

**헤더 파라미터:**

| 파라미터      | 타입    | 필수여부 | 설명               |
|--------------|--------|----------|--------------------|
| Content-Type | String | 필수     | application/json  |

**요청 파라미터:**

| 파라미터  | 타입   | 필수여부 | 설명     |
|----------|--------|----------|----------|
| keyword | String | 선택     | 검색 키워드 (없을 경우 전체 상품 검색) |
| page    | Number | 선택     | 페이지 번호 (기본값: 0) |
| size    | Number | 선택     | 페이지 당 아이템 수 (기본값: 5) |

**응답:**

```json
{
  "products": [
    {
      "id": 0,
      "name": "string",
      "price": 0
    }
  ],
  "page": 0,
  "size": 0,
  "totalSize": 0
}
```

**응답 필드:**

- `products`: 검색된 상품 목록
  - `id`: 상품 ID
  - `name`: 상품 이름
  - `price`: 상품 가격
- `page`: 현재 페이지 번호
- `size`: 페이지 당 아이템 수
- `totalSize`: 전체 상품 수

</details>

## 장바구니 

<details>
  <summary><h3>장바구니에 상품 추가</h3></summary>


```bash
curl -X POST http://35.232.189.24:8080/api/v1/cart/items \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer {token}' \
  -d '{
    "productId": 1,
    "quantity": 2
  }'
```

**헤더 파라미터:**

| 파라미터         | 타입    | 필수여부 | 설명                        |
|-----------------|--------|----------|-----------------------------|
| Content-Type    | String | 필수     | application/json           |
| Authorization   | String | 필수     | Bearer 토큰 (사용자 인증용) |

**바디 파라미터:**

| 파라미터     | 타입    | 필수여부 | 설명             |
|-------------|--------|----------|------------------|
| productId   | Number | 필수     | 상품 ID          |
| quantity    | Number | 필수     | 추가할 수량      |

**응답:**


```json
201
```
</details>

<details>
  <summary><h3>내 장바구니 조회</h3></summary>


```bash
curl -X GET http://35.232.189.24:8080/api/v1/cart/items/my \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer {token}'
```

**헤더 파라미터:**

| 파라미터         | 타입    | 필수여부 | 설명                        |
|-----------------|--------|----------|-----------------------------|
| Content-Type    | String | 필수     | application/json           |
| Authorization   | String | 필수     | Bearer 토큰 (사용자 인증용) |

**응답:**

```json
{
  "items": [
    {
      "id": 1,
      "quantity": 2,
      "product": {
        "id": 1,
        "name": "상품 이름",
        "price": 1000
      }
    }
  ],
  "totalPrice": 2000
}
```

**응답 필드 설명:**

- `items`: 장바구니에 담긴 상품 목록
  - `id`: 장바구니 아이템 ID
  - `quantity`: 상품 수량
  - `product`: 상품 정보
    - `id`: 상품 ID
    - `name`: 상품 이름
    - `price`: 상품 가격
- `totalPrice`: 장바구니에 담긴 상품들의 총 가격

</details>

<details>
  <summary><h3>장바구니 아이템 삭제</h3></summary>


```bash
curl -X DELETE http://35.232.189.24:8080/api/v1/cart/items/{itemId} \
  -H 'Authorization: Bearer {token}'
```

**헤더 파라미터:**

| 파라미터         | 타입    | 필수여부 | 설명                        |
|-----------------|--------|----------|-----------------------------|
| Authorization   | String | 필수     | Bearer 토큰 (사용자 인증용) |

**경로 파라미터:**

| 파라미터  | 타입   | 필수여부 | 설명             |
|----------|--------|----------|------------------|
| itemId   | Number | 필수     | 삭제할 아이템 ID |


**응답:**

```json
204
```
</details>

<details>
  <summary><h3>내 장바구니 모두 삭제</h3></summary>


```bash
curl -X DELETE http://35.232.189.24:8080/api/v1/cart/items/my \
  -H 'Authorization: Bearer {token}'
```

**헤더 파라미터:**

| 파라미터         | 타입    | 필수여부 | 설명                        |
|-----------------|--------|----------|-----------------------------|
| Authorization   | String | 필수     | Bearer 토큰 (사용자 인증용) |

**응답:**

```json
204
```
  </details>

## Order 

<details>
  <summary><h3>내 장바구니 주문</h3></summary>


```bash
curl -X POST http://35.232.189.24:8080/api/v1/order/cart/my \
  -H 'Authorization: Bearer {token}'
```

**헤더 파라미터:**

| 파라미터         | 타입    | 필수여부 | 설명                        |
|-----------------|--------|----------|-----------------------------|
| Authorization   | String | 필수     | Bearer 토큰 (사용자 인증용) |

**응답:**

```json
{
  "orderId": 1,
  "totalPrice": 2000,
  "createdAt": "2023-12-17T18:32:41.915Z"
}
```

**응답 필드 설명:**

- `orderId`: 주문 ID
- `totalPrice`: 주문 총 가격
- `createdAt`: 주문 생성 일자

</details>


<details>
  <summary><h3>내 주문내역 조회</h3></summary>

```bash
curl -X GET http://35.232.189.24:8080/api/v1/order/details/my \
  -H 'Authorization: Bearer {token}' \
  -d '{
    "page": 0,
    "size": 5
  }'
```

**헤더 파라미터:**

| 파라미터         | 타입    | 필수여부 | 설명                        |
|-----------------|--------|----------|-----------------------------|
| Authorization   | String | 필수     | Bearer 토큰 (사용자 인증용) |

**요청 파라미터:**

| 파라미터  | 타입   | 필수여부 | 설명             |
|----------|--------|----------|------------------|
| page     | Number | 선택     | 페이지 번호 (기본값: 0) |
| size     | Number | 선택     | 페이지 당 아이템 수 (기본값: 5) |


**응답:**

```json
{
  "orders": [
    {
      "id": 1,
      "totalPrice": 200.0,
      "createdAt": "2023-12-17T18:34:22.660Z",
      "orderItems": [
        {
          "quantity": 2,
          "orderPrice": 100.0,
          "product": {
            "id": 1,
            "name": "상품 이름",
            "price": 500
          }
        }
      ]
    }
  ],
  "page": 0,
  "size": 5,
  "totalSize": 1
}
```

**응답 필드:**

- `orders`: 주문 내역 목록
  - `id`: 주문 ID
  - `totalPrice`: 주문 총 가격
  - `createdAt`: 주문 생성 일자
  - `orderItems`: 주문에 속한 상품 목록
    - `quantity`: 상품 수량
    - `orderPrice`: 주문된 상품 가격
    - `product`: 상품 정보
      - `id`: 상품 ID
      - `name`: 상품 이름
      - `price`: 상품 가격
- `page`: 현재 페이지 번호
- `size`: 페이지 당 아이템 수
- `totalSize`: 전체 주문 내역 수


  </details>

</details>
