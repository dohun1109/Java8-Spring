package kr.co.shortenurlservice.domain;

import java.util.Random;

public class ShortenUrl {

    private String originUrl;
    private String shortenUrlKey;
    private Long redirectCount;

    public ShortenUrl(String originUrl, String shortenUrlKey) {
        this.originUrl = originUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.redirectCount = 0L;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    public static String generateShortenUrlKey() {
        String base56Characters = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";

        Random random = new Random();
        StringBuilder shortenUrlKey = new StringBuilder();

        for (int count = 0; count < 8; count++) {
            int base56CharactersIndex = random.nextInt(0, base56Characters.length());
            char base56Character = base56Characters.charAt(base56CharactersIndex);
            shortenUrlKey.append(base56Character);
        }

        return shortenUrlKey.toString();
    }

    public void increaseRedirectCount() {
        this.redirectCount +=1;
    }
}
