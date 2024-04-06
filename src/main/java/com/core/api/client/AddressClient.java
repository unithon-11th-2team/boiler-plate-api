package com.core.api.client;

import com.core.api.config.kakao.KakaoConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressClient {
    private final KakaoConfig kakaoConfig;
    private final RestTemplate restTemplate;

    /**
     * curl -v -G GET "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=127.1086228&y=37.4012191" \
     * -H "Authorization: KakaoAK ${REST_API_KEY}"
     */
    public KakaoApiResponse search(BigDecimal latitude, BigDecimal longitude) {
        log.info("kakao token " + kakaoConfig.getToken());

        // HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoConfig.getToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v2/local/geo/coord2regioncode.json")
                .queryParam("x", longitude)
                .queryParam("y", latitude);

        // GET 요청 수행
        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                KakaoApiResponse.class
        ).getBody();
    }
}
