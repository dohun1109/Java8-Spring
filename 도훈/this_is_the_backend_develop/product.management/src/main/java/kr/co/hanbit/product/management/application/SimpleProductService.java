package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.domain.ProductRepository;
import kr.co.hanbit.product.management.infrastructure.DatabaseProductRepository;
import kr.co.hanbit.product.management.infrastructure.ListProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

//    private final ModelMapper modelMapper;
//    private ListProductRepository listProductRepository;
    private ProductRepository productRepository;
    private ValidationService validationService;

    @Autowired
    public SimpleProductService(ProductRepository productRepository, ValidationService validationService) {
        this.productRepository = productRepository;
//        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }

    /**
     * ModelMapper 의 map 메서드를 사용할 때는 첫 번째 인자로 변환시킬 대상을 넣고, 두번째 인자로 어떤 타입으로 변환할지
     * [클래스이름.class]의 형태로 넣어 주면 된다. 그러면 필드 이름을 기준으로 동일한 필드 이름에 해당하는 값을 자동으로 복사하여
     * 변환해 준다.
     * @param productDto
     * @return
     */
    public ProductDto add(ProductDto productDto) {
        //1. Product 를 Product로 변환하는 코드
        Product product = ProductDto.toEntity(productDto);
        validationService.checkValid(product);
        //2. 레포지토리를 호출하는 코드
        Product savedProduct = productRepository.add(product);

        //3. Product 를 Product 로 변환하는 코드
        ProductDto savedProductDto = ProductDto.toDto(savedProduct);

        //4. DTO 를 반환하는 코드
        return savedProductDto;
    }


    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        ProductDto productDto = ProductDto.toDto(product);
        return productDto;
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.toDto(product))
                .toList();
        return productDtos;
    }

    public List<ProductDto> findByNameContaining(String name) {
        List<Product> products = productRepository.findByNameContaining(name);

        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.toDto(product))
                .toList();
        return productDtos;
    }

    public ProductDto update(ProductDto productDto) {
        Product product = ProductDto.toEntity(productDto);
        Product updateProduct = productRepository.update(product);
        ProductDto updateProductDto = ProductDto.toDto(updateProduct);

        return updateProductDto;
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
