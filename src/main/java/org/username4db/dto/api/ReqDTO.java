package org.username4db.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReqDTO {

	@Schema(description = "@Schema description", example = " @Schema example ", required = true)
	@JsonProperty(value = "INPUT-KEY", required = true)
	private String key;

	@Schema(description = "@Schema description", example = " @Schema example ", required = true)
	@JsonProperty(value = "INPUT-VALUE", required = true)
	private String value;

	@Schema(description = "@Schema description", example = " @Schema example ", required = true)
	@JsonProperty(value = "INPUT-CAPTION", required = true)
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
