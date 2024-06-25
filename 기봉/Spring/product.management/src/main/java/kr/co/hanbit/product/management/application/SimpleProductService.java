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

    private ProductRepository productRepository;
    private ValidationService validationService;

    @Autowired
    SimpleProductService(ProductRepository productRepository, ValidationService validationService){
        this.productRepository = productRepository;
        this.validationService = validationService;
    }

    public ProductDto add(ProductDto productDto){
        // 1. ProductDto를 Product로 변환하는 코드
        Product product = ProductDto.toEntity(productDto);
        validationService.checkValid(product);

        // 2. 레포지토리를 호출하는 코드
        Product savedProduct = productRepository.add(product);

        // 3. Product를 ProductDTO로 변환하는 코드
        ProductDto savedProductDto = ProductDto.toDto(savedProduct);

        // 4. DTO를 반환하는 코드
        return savedProductDto;
    }

    public ProductDto findById(Long id){
        Product product = productRepository.findById(id);
        ProductDto productDto = ProductDto.toDto(product);
        return productDto;
    }

    public List<ProductDto> findAll(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.toDto(product))
                .toList();
        return productDtos;
    }

    public List<ProductDto> findByNameContaining(String name){
        List<Product> products = productRepository.findByNameContaining(name);
        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.toDto(product))
                .toList();
        return productDtos;
    }

    public ProductDto update(ProductDto productDto) {
        Product product = ProductDto.toEntity(productDto);
        Product updatedProduct = productRepository.update(product);
        ProductDto updatedProductDto = ProductDto.toDto(updatedProduct);
        return updatedProductDto;
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}