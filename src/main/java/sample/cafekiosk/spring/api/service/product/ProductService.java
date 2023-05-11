package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * readOnly = true : 읽기전용 -> CRUD 에서 CUD 동작 X
 * JPA : 리드온리 true 면 스냅샷 저장, 변경감지를 하지 않음. -> 성능 향상
 *
 * CQRS - Command / Query 서비스를 분리  (Read 작업이 빈도가 높음.)
 * 쿼리용 서비스와 커맨드용 서비스 분리 가능.
 * -> DB도 리드 용과 write 용 으로 나누기도 가능해짐.
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductNumberFactory productNumberFactory;

    // 동시성 이슈 / productNumber에 유니크 제약조건을 걸고 같은 번호가 등록되었을때 재시도 하는 방법이 있을 수 있다.
    // UUID 같은 값을 활용하면 동시성 문제를 풀 수 있다. (한 번에 많이 등록된다하면)
    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        String nextProductNumber = productNumberFactory.createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }


}
