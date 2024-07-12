package vaildation;

import com.sun.jdi.Field;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {
    // 검증 오류 코드로 메시지 코드들을 생성한다.
    // MessageCodesRecolver은 인터페이스
    // DefaultMessageCodesResolver()은 구현체이다.
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] messagesCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messagesCode : messagesCodes) {
            System.out.println("messagesCode = " + messagesCode);
        }
        assertThat(messagesCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );
    }
 }
