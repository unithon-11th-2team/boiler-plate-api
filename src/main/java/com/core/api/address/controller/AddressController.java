package com.core.api.address.controller;

import com.core.api.client.AddressClient;
import com.core.api.client.KakaoApiResponse;
import com.core.api.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Tag(name = "주소 관리")
@RestController
@RequestMapping(path = "/api/v1/address", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AddressController {
    private final AddressClient addressClient;

    @Operation(summary = "지역정보 조회")
    @GetMapping
    public ResponseEntity<ResponseDto<KakaoApiResponse>> getAddress(
            @RequestParam BigDecimal latitude,
            @RequestParam BigDecimal longitude
    ) {
        var response = addressClient.search(latitude, longitude);
        return ResponseDto.ok(response);
    }
}
