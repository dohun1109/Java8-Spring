package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.presentation.ShortenUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.ShortenUrlCreateResponseDto;
import org.springframework.stereotype.Service;

@Service
public class SimpleShortenUrlService {

    /**
     * 1. 단축 URL키 생성
     * 2. 원래의 URL과 단축 URL키를 통해 ShortenUrl 도메인 객체 생성
     * 3. 생성된 ShortenUrl 을 레포지토리를 통해 저장
     * 4. ShortenUrl 을 ShortenUrlCreateResponseDto 로 변환하여 반환
     * @param shortenUrlCreateRequestDto
     * @return
     */
    public ShortenUrlCreateResponseDto generateShortenUrl(
            ShortenUrlCreateRequestDto shortenUrlCreateRequestDto
    ) {

        return null;
    }
}
