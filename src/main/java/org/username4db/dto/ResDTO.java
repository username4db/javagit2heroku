package org.username4db.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ResDTO {

	private String key;
	private String value;
	private BigDecimal decimal;
	private LocalDateTime datetime;

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

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "ResDTO [key=" + key + ", value=" + value + ", decimal=" + decimal + ", datetime=" + datetime + "]";
	}

}
