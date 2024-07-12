package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.infrastructure.ListProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

    private final ModelMapper modelMapper;
    private ListProductRepository listProductRepository;

    @Autowired
    public SimpleProductService(ListProductRepository listProductRepository, ModelMapper modelMapper) {
        this.listProductRepository = listProductRepository;
        this.modelMapper = modelMapper;
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
        Product product = modelMapper.map(productDto, Product.class);

        //2. 레포지토리를 호출하는 코드
        Product savedProduct = listProductRepository.add(product);

        //3. Product 를 Product 로 변환하는 코드
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        //4. DTO 를 반환하는 코드
        return savedProductDto;
    }


    public ProductDto findById(Long id) {
        Product product = listProductRepository.findById(id);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    public List<ProductDto> findAll() {
        List<Product> products = listProductRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return productDtos;
    }

    public List<ProductDto> findByNameContaining(String name) {
        List<Product> products = listProductRepository.findByNameContaining(name);

        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return productDtos;
    }

    public ProductDto update(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product updateProduct = listProductRepository.update(product);
        ProductDto updateProductDto = modelMapper.map(updateProduct, ProductDto.class);

        return updateProductDto;
    }
    
}
