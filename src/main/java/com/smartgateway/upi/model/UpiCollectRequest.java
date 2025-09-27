package com.smartgateway.upi.model;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpiCollectRequest {
	
    private String merchantId;
    private String txnId;
    private String amount;
    private String customerVpa;
    private String callbackUrl;
    
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCustomerVpa() {
		return customerVpa;
	}
	public void setCustomerVpa(String customerVpa) {
		this.customerVpa = customerVpa;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, callbackUrl, customerVpa, merchantId, txnId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpiCollectRequest other = (UpiCollectRequest) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(callbackUrl, other.callbackUrl)
				&& Objects.equals(customerVpa, other.customerVpa) && Objects.equals(merchantId, other.merchantId)
				&& Objects.equals(txnId, other.txnId);
	}
    
    

}
