package com.emazon.microservicio_transaccion.adapters.driving.controller;

import com.emazon.microservicio_transaccion.adapters.driving.dto.request.AddSaleRequest;
import com.emazon.microservicio_transaccion.adapters.driving.mapper.ISaleRequestMapper;
import com.emazon.microservicio_transaccion.adapters.driving.util.DrivingConstants;
import com.emazon.microservicio_transaccion.domain.api.ISaleServicePort;
import com.emazon.microservicio_transaccion.domain.model.Sale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sale")
@RequiredArgsConstructor
@Tag(name = DrivingConstants.TAG_SALE_NAME, description = DrivingConstants.TAG_SALE_DESCRIPTION)
public class SaleRestController {
    private final ISaleRequestMapper saleRequestMapper;
    private final ISaleServicePort saleServicePort;

    @Operation(summary = DrivingConstants.SAVE_SALE_SUMMARY, description = DrivingConstants.SAVE_SALE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_201, description = DrivingConstants.SAVE_SALE_RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = DrivingConstants.RESPONSE_CODE_400, description = DrivingConstants.SAVE_SALE_RESPONSE_400_DESCRIPTION, content = @Content)
    })
    @PreAuthorize(DrivingConstants.HAS_ROLE_CLIENT)
    @PostMapping
    public ResponseEntity<HttpStatus> addSale(@Valid @RequestBody AddSaleRequest request) {
        Sale sale = saleRequestMapper.addRequestToSale(request);
        saleServicePort.saveSale(sale);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
