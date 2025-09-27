package com.smartgateway.upi.model;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpiCollectResponse {
	
    private String txnId;
    private String qrCodeUrl;
    private String deepLink;
    private String status;
	@Override
	public int hashCode() {
		return Objects.hash(deepLink, qrCodeUrl, status, txnId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpiCollectResponse other = (UpiCollectResponse) obj;
		return Objects.equals(deepLink, other.deepLink) && Objects.equals(qrCodeUrl, other.qrCodeUrl)
				&& Objects.equals(status, other.status) && Objects.equals(txnId, other.txnId);
	}

}
