package com.peterpet.demo.module.payment;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentPeterController {

	@Value("${secret-key}")
	private String tossSecretKey;
	
	@Autowired
	PaymentService paymentService;
	
	@RequestMapping(value = "/success")
	public String shopPeterSuccess(@ModelAttribute("pay") PaymentDto dto) {
		return "peter/shop/success";
	}
	
	@RequestMapping(value = "/fail")
	public String shopPeterFail() {
		return "peter/shop/fail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/confirm/payment")
	public ResponseEntity<String> payment(@RequestBody Map<String, Object> payload,
			HttpSession httpSession) throws IOException, InterruptedException {
		PaymentDto dto = new PaymentDto();
		dto.setPaymentKey((String) payload.get("paymentKey"));
		dto.setOrderId((String) payload.get("orderId"));
	    dto.setAmount(Integer.valueOf(payload.get("amount").toString()));
	    
	    String json = String.format("{\"paymentKey\":\"%s\",\"amount\":%d,\"orderId\":\"%s\"}",
                dto.getPaymentKey(), dto.getAmount(), dto.getOrderId());
	    String authValue = Base64.getEncoder().encodeToString((tossSecretKey + ":").getBytes());
	    
	    HttpRequest request = HttpRequest.newBuilder()
    	    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
    	    .header("Authorization", "Basic " + authValue)
    	    .header("Content-Type", "application/json")
    	    .method("POST", HttpRequest.BodyPublishers.ofString(json))
    	    .build();
    	HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    	String responseBody = response.body();
    	System.out.println(responseBody);
    	
    	String temp = extractValueFromJson(responseBody, "orderName");
	    String quantity = "";
	    for (int i = temp.lastIndexOf(" ") + 1; i < temp.length() - 1; i++) {
	    	quantity += temp.charAt(i);
	    }
	    dto.setOrderName(temp.substring(0, temp.lastIndexOf(" ")));
	    dto.setQuantity(Integer.parseInt(quantity));
	    dto.setUserSeq(httpSession.getAttribute("sessSeqPeter").toString());
	    dto.setVat(Integer.valueOf(extractValueFromJson(responseBody, "vat")));

    	if (extractValueFromJson(responseBody, "provider") != null) {
    		dto.setEasyPay(extractValueFromJson(responseBody, "provider"));	    	
	    } else {
	    	dto.setEasyPay(extractValueFromJson(responseBody, "cardType"));
	    }
    	
    	dto.setRequestedAt(extractValueFromJson(responseBody, "requestedAt").substring(0, 10));
    	dto.setApprovedAt(extractValueFromJson(responseBody, "approvedAt").substring(0, 10));
    	dto.setPayStatus(273);
    	
    	paymentService.insert(dto);
    	
    	return ResponseEntity.status(response.statusCode()).body(response.body());
	}
	
	private static String extractValueFromJson(String json, String key) {
        int keyIndex = json.indexOf(key);
        if (keyIndex == -1) {
            return null; // 키가 없으면 null 반환
        }

        int startIndex = json.indexOf(":", keyIndex) + 1;
        int endIndex = json.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = json.indexOf("}", startIndex);
        }

        return json.substring(startIndex, endIndex).replace("\"", "").trim(); // 따옴표 제거하고 반환
    }
	
	@RequestMapping(value = "/PaymentPeterProc")
	public String paymentPeterProc(@ModelAttribute("vo2") PaymentVo paymentVo, Model model) {
		paymentVo.setParamsPaging(paymentService.selectCount(paymentVo));
		model.addAttribute("pays", paymentService.selectList(paymentVo));
	    
	    return "peter/include/payList :: payList";
	}
}
