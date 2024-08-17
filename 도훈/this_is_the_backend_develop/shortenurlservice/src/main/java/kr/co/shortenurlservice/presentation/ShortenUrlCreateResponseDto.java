package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.domain.ShortenUrl;

public class ShortenUrlCreateResponseDto {

    private String originalUrl;
    private String shortenUrlKey;

    public ShortenUrlCreateResponseDto(ShortenUrl shortenUrl) {
        this.originalUrl = shortenUrl.getOriginUrl();
        this.shortenUrlKey = shortenUrl.getShortenUrlKey();
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}
