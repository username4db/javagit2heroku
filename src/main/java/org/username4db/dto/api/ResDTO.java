package org.username4db.dto.api;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResDTO {

	@JsonProperty(value = "OUTPUT-KEY")
	private String key;

	@JsonProperty(value = "OUTPUT-VALUE")
	private String value;

	@JsonProperty(value = "OUTPUT-DECIMAL")
	private BigDecimal decimal;

	@JsonProperty(value = "OUTPUT-SERVERTIME")
	private LocalDateTime serverDatetime;

	@JsonProperty(value = "OUTPUT-CLIENTTIME")
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
