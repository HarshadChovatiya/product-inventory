package com.catalog.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catalog.model.CartData;
import com.catalog.model.JsonResponse;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderServiceClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/order/addtocart")
	public ResponseEntity<JsonResponse> callAddToCart(
			@RequestBody CartData cartData,
			@RequestHeader("UserName") String username
			);
	
}
