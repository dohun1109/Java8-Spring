package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.infrastructure.ListProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleProductService {

    private ListProductRepository listProductRepository;

    @Autowired
    SimpleProductService(ListProductRepository listProductRepository){
        this.listProductRepository = listProductRepository;
    }

    public ProductDto add(ProductDto productDto){
        // 1. ProductDto를 Product로 변환하는 코드
        Product product = ??;

        // 2. 레포지토리를 호출하는 코드
        Product savedProduct = listProductRepository.add(product);

        // 3. Product를 ProductDTO로 변환하는 코드
        ProductDto savedProductDto = ??;

        // 4. DTO를 반환하는 코드
        return savedProductDto;


    }

}
