package org.username4db.dto.api;

public class ReqDTO {

	private String key;
	private String value;
	private String valueCapition;

	public String getValueCapition() {
		return valueCapition;
	}

	public void setValueCapition(String valueCapition) {
		this.valueCapition = valueCapition;
	}

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

	@Override
	public String toString() {
		return "ReqDTO [key=" + key + ", value=" + value + ", valueCapition=" + valueCapition + "]";
	}

}
