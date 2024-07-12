package hello.item_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemServiceApplication {

	/**
	 * 상품 도메인 모델
	 * 	- 상품 ID
	 * 	- 상품명
	 * 	- 가격
	 * 	- 수량
	 *
	 * 	상품 관리 기능
	 * 		- 상품 목록
	 * 	 	- 상품 상세
	 * 	 	- 상품 등록
	 * 	  	- 상품 수정 	
	 */
	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

}
