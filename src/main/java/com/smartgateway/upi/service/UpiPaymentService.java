package com.smartgateway.upi.service;

import com.smartgateway.upi.model.UpiCollectResponse;

public interface UpiPaymentService {
	
	 public UpiCollectResponse createCollectRequest(String amount, String customerVpa) throws Exception;
	 
	 public String checkTransactionStatus(String txnId) throws Exception;

}
