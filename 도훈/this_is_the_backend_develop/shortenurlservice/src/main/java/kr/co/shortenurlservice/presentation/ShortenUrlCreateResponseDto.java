package kr.co.shortenurlservice.presentation;

public class ShortenUrlCreateResponseDto {

    private String originalUrl;
    private String shortenUrlKey;

    public ShortenUrlCreateResponseDto(String originalUrl, String shortenUrlKey) {
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}
