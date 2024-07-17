package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.domain.ProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimpleProductServiceUnitTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private SimpleProductService simpleProductService;


    @Test
    @DisplayName("상품 추가 후에는 추가도니ㅐ 상품이 반환되어야한다.")
    void productAddTest() {
        ProductDto productDto = new ProductDto("연필", 300, 20);
        Long PRODUCT_ID = 1L;

        Product product = ProductDto.toEntity(productDto);
        product.setId(PRODUCT_ID);
        /**
         * when-> 목 객체가 어떻게 행동해야 하는지 정의, thenReturn -> 목 객체가 when에 해당하는 동작을 수행할 때 thenReturn에 있는 값을 반환한다.
         * @Mock 이라는 애너테이션은 해당 의존성에 '목 객체'를 주입한다는 의미이다. 목 객체를 주입하는 것만으로는 아무 기능을 하지 않는다.
         * 테스트 코드에서 목 객체가 어떤 메서드에 대해 어떤 동작을 할지는 직접 정의해야 한다.
         * @InjectMocks 라는 애너테이션을 붙여 줬다. 이애너테이션은 위에서 @Mock 으로 주입해준 목 객체들을 SimpleProductService 내에 있는 의존성에 주입해
         * 주는 역할을 한다. 목 객체들을 주입받는 대상인 SimpleProductService 는 목 객체가 아니라 실제 인스턴스를 생성하여 로직으로 사용할 수 있다.
         */
        when(productRepository.add(any())).thenReturn(product); //any() -> 아무 값


        ProductDto savedProductDto = simpleProductService.add(productDto);

        assertTrue(savedProductDto.getId().equals(PRODUCT_ID));

        assertTrue(savedProductDto.getName().equals(productDto.getName()));
        assertTrue(savedProductDto.getPrice().equals(productDto.getPrice()));
        assertTrue(savedProductDto.getAmount().equals(productDto.getAmount()));
        
    }














}
