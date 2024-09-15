package com.emazon.microservicio_transaccion.adapters.driving.controller;

import com.emazon.microservicio_transaccion.adapters.driving.dto.request.AddSupplyRequest;
import com.emazon.microservicio_transaccion.adapters.driving.dto.response.SupplyResponse;
import com.emazon.microservicio_transaccion.adapters.driving.mapper.ISupplyRequestMapper;
import com.emazon.microservicio_transaccion.adapters.driving.mapper.ISupplyResponseMapper;
import com.emazon.microservicio_transaccion.adapters.driving.util.DrivingConstants;
import com.emazon.microservicio_transaccion.domain.api.ISupplyServicePort;
import com.emazon.microservicio_transaccion.domain.model.Supply;
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
@RequestMapping("/api/v1/supply")
@RequiredArgsConstructor
public class SupplyRestController {
    private final ISupplyServicePort supplyServicePort;
    private final ISupplyResponseMapper supplyResponseMapper;

    @PreAuthorize(DrivingConstants.HAS_ROLE_AUX_BODEGA)
    @PostMapping
    public ResponseEntity<SupplyResponse> addSupply(@Valid @RequestBody AddSupplyRequest request) {
        Supply supply = ISupplyRequestMapper.addRequestToSupply(request);
        Supply supplyFinal = supplyServicePort.saveSupply(supply);

        SupplyResponse response = supplyResponseMapper.toSupplyResponse(supplyFinal);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
