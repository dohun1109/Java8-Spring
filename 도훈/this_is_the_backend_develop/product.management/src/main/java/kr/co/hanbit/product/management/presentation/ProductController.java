package kr.co.hanbit.product.management.presentation;

import kr.co.hanbit.product.management.application.SimpleProductService;
import kr.co.hanbit.product.management.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final SimpleProductService simpleProductService;

    @Autowired
    public ProductController(SimpleProductService simpleProductService) {
        this.simpleProductService = simpleProductService;
    }

    // 상품 추가
    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        // Product 를 생성하고 리스트에 넣는 작업이 필요함.(clear)
        return simpleProductService.add(productDto);
    }

    // 식별자로 단일 상품 조회
    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return simpleProductService.findById(id);
    }

    // 전체 상품 목록 조회 또는 이름으로 검색
    @GetMapping
    public List<ProductDto> findProducts(@RequestParam(required = false) String name) {
        if (name == null) {
            return simpleProductService.findAll();
        }
        return simpleProductService.findByNameContaining(name);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto productDto
    ) {
        productDto.setId(id);
        return simpleProductService.update(productDto);
    }
}
