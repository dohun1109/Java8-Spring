package kr.co.hanbit.product.management;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.ui.ModelMap;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * ModelMapper 의 기본 설정은 '매개변수가 없는 생성자(기본 생성자)로 인스턴스로 생성한 후 setter로 값을 초기화하여 변환'하는 것이다.
	 *
	 * 실무에서는 getter는 데이터베이스에 접근하거나 DTO 로 도메인 객체를 변환하는 과정(ModelMapper 를 사용하여 제거하긴 했지만)
	 * 이 필요한 경우가 꽤 있으므로 필요할 때 구현하도록 하자. 반면, setter 는 생성자를 사용하거나 표현력 있는 메서드를 활용하여 대체가 가능하다.
	 * @return
	 */
//	@Bean
//	public ModelMapper modelMapper(){
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration()
//				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
//				.setFieldMatchingEnabled(true);
//		return modelMapper;
//	}

	@Bean
	@Profile("prod")
	public ApplicationRunner runner(DataSource dataSource) {
		return args ->{
			//이 부분에 실행할 코드를 넣으면 된다.
			Connection connection = dataSource.getConnection();
		};
	}

}
