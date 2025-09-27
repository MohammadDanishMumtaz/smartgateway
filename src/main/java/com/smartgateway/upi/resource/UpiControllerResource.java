package com.smartgateway.upi.resource;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartgateway.upi.model.UpiCollectResponse;

public interface UpiControllerResource {
	
	public UpiCollectResponse startPayment(@RequestParam String amount,@RequestParam String vpa) throws Exception;
	
	public ResponseEntity<String> handleCallback(@RequestBody Map<String,String> payload) throws Exception;

}
