package kr.co.hanbit.product.management.infrastructure;

import kr.co.hanbit.product.management.domain.EntityNotFoundException;
import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.domain.ProductRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("test")
public class ListProductRepository implements ProductRepository {

    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    public Product add(Product product) {

        product.setId(sequence.getAndAdd(1L));
        
        products.add(product);
        return product;
    }

    /**
     * Product 가 들어 있는 리스트에서 상품번호를 기준으로 하나의 Product를 뽑는 메서드
     * products 리스트에 대해 스트림 API를 활용하여 filter의 결과가 참인 Product만 뽑아내는 코드
     * @param id
     * @return .findFirst() : streamAPI의 filter에 걸린 Product 중 첫 번째 Product에 대한 Optional 객체를 반환
     * Optional 객체는 비어있는 상태일 수 도 있고, Product가 들어있을 수도 있다. 주의할 점은 타입이 Optional<Product>이기 때문에
     * 바로 Product 반환할 수 없다.
     * .orElseThrow(): 해당 Optional 객체가 비어있으면 'NoSuchElementException'라는 이름의 예외를던지고, Product가 들어있으면 Product반환
     */
    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product를 찾지 못했습니다."));
    }

    public List<Product> findAll(){
        return new ArrayList<>(products);
    }

    public List<Product> findByNameContaining(String name) {
        return products.stream()
                .filter(product -> product.containsName(name))
                .toList();
    }

    public Product update(Product product) {
        Integer indexToModify = products.indexOf(product);
        products.set(indexToModify, product);
        return product;
    }


    public void delete(Long id) {
        Product product = this.findById(id);
        products.remove(product);
    }
}
