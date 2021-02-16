package org.username4db.dto.hangul;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResHangul {

	@JsonProperty(value = "CHARACTERS")
	private String chracters;

	@Override
	public String toString() {
		return "ResHangul [chracters=" + chracters + "]";
	}

	public String getChracters() {
		return chracters;
	}

	public void setChracters(String chracters) {
		this.chracters = chracters;
	}

}
