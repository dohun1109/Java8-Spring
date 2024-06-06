package kr.co.hanbit.product.management.infrastructure;

import kr.co.hanbit.product.management.domain.EntityNotFoundException;
import kr.co.hanbit.product.management.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListProductRepository {

    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    public Product add(Product product){
        product.setId(sequence.getAndAdd(1L));

        products.add(product);
        return product;
    }

    public Product findById(Long id){
        // products 리스트를 스트림으로 변환
        return products.stream()
                // filter : 주어진 조건에 맞는 요소만 추출
                .filter(product -> product.sameId(id))
                // findFirst : 스트림의 첫번째 요소를 Optional형태로 반환
                .findFirst()
                // orElseThrow : Optional 객체가 비어있을 경우 예외를 발생
                .orElseThrow(() -> new EntityNotFoundException("Product를 찾지 못했습니다."));
    }

    public List<Product> findAll(){
        return products;
    }

    public List<Product> findByNameContaining(String name){
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
