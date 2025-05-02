package com.peterpet.demo.module.product;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaymentController {

	@Value("${secret-key}")
	private String tossSecretKey;
	
	@RequestMapping(value = "/success")
	public String shopPeterSuccess(@RequestParam(name = "paymentKey") String paymentKey,
			@RequestParam(name = "orderId") String orderId, @RequestParam(name = "amount") int amount) {
		return "peter/shop/success";
	}
	
	@RequestMapping(value = "/fail")
	public String shopPeterFail() {
		return "peter/shop/fail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/confirm/payment")
	public ResponseEntity<String> payment(@RequestBody Map<String, Object> payload) throws IOException, InterruptedException {
		String paymentKey = (String) payload.get("paymentKey");
	    String orderId = (String) payload.get("orderId");
	    Integer amount = Integer.valueOf(payload.get("amount").toString());
	    
	    String json = String.format("{\"paymentKey\":\"%s\",\"amount\":%d,\"orderId\":\"%s\"}",
                paymentKey, amount, orderId);
	    String authValue = Base64.getEncoder().encodeToString((tossSecretKey + ":").getBytes());
	    
	    HttpRequest request = HttpRequest.newBuilder()
    	    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
    	    .header("Authorization", "Basic " + authValue)
    	    .header("Content-Type", "application/json")
    	    .method("POST", HttpRequest.BodyPublishers.ofString(json))
    	    .build();
    	HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    	System.out.println(response.body());
    	
    	return ResponseEntity.status(response.statusCode()).body(response.body());
	}
}
