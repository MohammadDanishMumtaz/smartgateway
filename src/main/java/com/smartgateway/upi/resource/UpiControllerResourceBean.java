package com.smartgateway.upi.resource;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartgateway.upi.model.UpiCollectResponse;
import com.smartgateway.upi.service.EmailService;
import com.smartgateway.upi.service.UpiPaymentService;

@RestController
@RequestMapping("/api/upi")
public class UpiControllerResourceBean implements UpiControllerResource {
	
	@Autowired
	private UpiPaymentService upiService;
	
	@Autowired
	 private EmailService emailService;

	@PostMapping("/pay")
	public UpiCollectResponse startPayment(@RequestParam String amount,@RequestParam String vpa) throws Exception {
		  return upiService.createCollectRequest(amount, vpa);
	}

	@PostMapping("/callback")
	public ResponseEntity<String> handleCallback(@RequestBody Map<String,String> payload) throws Exception {
		String txnId = payload.get("txnId");
        String status = payload.get("status");
        String email = payload.get("customerEmail");

        if ("SUCCESS".equalsIgnoreCase(status)) {
            emailService.sendEbookLink(email, "https://yourserver.com/download/ebook123.pdf");
        }
        return ResponseEntity.ok("OK");
	}


}
