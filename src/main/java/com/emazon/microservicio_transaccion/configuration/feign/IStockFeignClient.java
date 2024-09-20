package com.emazon.microservicio_transaccion.configuration.feign;

import com.emazon.microservicio_transaccion.adapters.driving.dto.request.AddSupplyRequest;
import com.emazon.microservicio_transaccion.configuration.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = Constants.STOCK_SERVICE_NAME, url = Constants.STOCK_SERVICE_URL, configuration = FeignClientInterceptor.class)
public interface IStockFeignClient {
    @PatchMapping("/update-quantity")
    ResponseEntity<Void> updateProductQuantity(@RequestBody AddSupplyRequest request);
}
