package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.presentation.ShortenUrlRestController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 컨트롤러를 단위 테스트하기 위해 필요한 애너테이션, 매개변수 지정해준 controllers 라는 값에 따라 테스트할 컨트롤러를 지정하는 코드이다.
 * 또한, 컨트롤러에 대한 단위테스트에서는 서비스에 대한 단위 테스트와 달리 컨트롤러에 대한 빈을 실제로 생성하낟.
 */
@WebMvcTest(controllers = ShortenUrlRestController.class)
public class ShortenUrlRestControllerTest {

    @MockBean   //@Mock 애너테이션과 비슷한 역활을 하지만 스프링 애플리케이션 컨테이너가 실행되기 때문에 컨테이너 빈을 생성해 줘야만 컨트롤러에서 해당 빈을 주입받아서 코드를 생행시킬 수 있다.
    private SimpleShortenUrlService simpleShortenUrlService;

    @Autowired
    private MockMvc mockMvc;    //컨트롤러를 테스트하기 위한 기능을 가지고 있는 클래스이다.
    //컨ㅌ트롤러를 테스트할 때는 컨트롤러의 메서드를 직접 호출하는 것이 아니라 MockMvc 를 통해 테스트해야 한다. 그래야 실제 WAS 와 통신하는 것처럼 테스트 할 수 있다.

    @Test
    @DisplayName("원래의 URL로 리다이렉트 되어야한다.")
    void redirectTest() throws Exception {
        String expectedOriginalUrl = "https://www.hanbit.co.kr/";

        when(simpleShortenUrlService.getOriginalUrlByShortenUrlKey(any())).thenReturn(expectedOriginalUrl);

        mockMvc.perform(get("/any-key"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", expectedOriginalUrl));
    }
}
