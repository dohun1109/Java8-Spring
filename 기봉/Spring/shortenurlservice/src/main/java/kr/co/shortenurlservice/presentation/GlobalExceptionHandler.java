package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.domain.LackOfShortenUrlKeyException;
import kr.co.shortenurlservice.domain.NotFoundShortenUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundShortenUrlException.class)
    public ResponseEntity<String> handleNotFoundShortenUrlException(
            LackOfShortenUrlKeyException ex
    ) {
        // 개발자에게 알려 줄 수 있는 수단 필요
        return new ResponseEntity<>("단축 URL 자원이 부족합니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
