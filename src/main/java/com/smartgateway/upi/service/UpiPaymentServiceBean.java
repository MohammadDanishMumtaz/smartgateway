package com.smartgateway.upi.service;

import java.util.UUID;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartgateway.upi.model.UpiCollectRequest;
import com.smartgateway.upi.model.UpiCollectResponse;

@Service
public class UpiPaymentServiceBean implements UpiPaymentService {
	
    @Value("${upi.bankUrl}") 
    private String bankUrl;
    
    @Value("${upi.merchantId}") 
    private String merchantId;
    
    @Value("${upi.apiKey}") 
    private String apiKey;
    
    @Value("${upi.statusUrl}") 
    private String statusUrl;
    
    private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public UpiCollectResponse createCollectRequest(String amount, String customerVpa) throws Exception {
		  UpiCollectRequest request = new UpiCollectRequest();
	        request.setMerchantId(merchantId);
	        request.setTxnId(UUID.randomUUID().toString());
	        request.setAmount(amount);
	        request.setCustomerVpa(customerVpa);
	        request.setCallbackUrl("https://yourserver.com/api/upi/callback");

	        String json = mapper.writeValueAsString(request);

	        HttpPost post = new HttpPost(bankUrl);
	        post.addHeader("Content-Type", "application/json");
	        post.addHeader("X-API-KEY", apiKey);
	        post.setEntity(new StringEntity(json, org.apache.hc.core5.http.ContentType.APPLICATION_JSON));

	        try (CloseableHttpClient client = HttpClients.createDefault();
	             CloseableHttpResponse response = client.execute(post)) {

	            String resp = EntityUtils.toString(response.getEntity());
	            return mapper.readValue(resp, UpiCollectResponse.class);
	        }
	}

	@Override
	public String checkTransactionStatus(String txnId) throws Exception {
		String url = statusUrl + "?merchantId=" + merchantId + "&txnId=" + txnId;
        HttpGet get = new HttpGet(url);
        get.addHeader("X-API-KEY", apiKey);

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(get)) {
            String resp = EntityUtils.toString(response.getEntity());
            JsonNode node = mapper.readTree(resp);
            return node.get("status").asText();
        }
	}

}
