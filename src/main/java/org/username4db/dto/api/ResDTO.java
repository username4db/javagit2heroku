package org.username4db.dto.api;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResDTO {

	private String key;
	private String value;
	private BigDecimal decimal;
	private LocalDateTime serverDatetime;
	@JsonFormat(timezone = "Asia/Taipei")
	private LocalDateTime clientDatetime;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BigDecimal getDecimal() {
		return decimal;
	}

	public void setDecimal(BigDecimal decimal) {
		this.decimal = decimal;
	}

	public LocalDateTime getServerDatetime() {
		return serverDatetime;
	}

	public void setServerDatetime(LocalDateTime serverDatetime) {
		this.serverDatetime = serverDatetime;
	}

	public LocalDateTime getClientDatetime() {
		return clientDatetime;
	}

	public void setClientDatetime(LocalDateTime clientDatetime) {
		this.clientDatetime = clientDatetime;
	}

	@Override
	public String toString() {
		return "ResDTO [key=" + key + ", value=" + value + ", decimal=" + decimal + ", serverDatetime=" + serverDatetime
				+ ", clientDatetime=" + clientDatetime + "]";
	}

}
