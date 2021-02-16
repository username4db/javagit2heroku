package org.username4db.dto.hangul;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqHangul {

	@JsonProperty(value = "CHARACTERS", required = true)
	private String characters;

	public String getCharacters() {
		return characters;
	}

	public void setCharacters(String characters) {
		this.characters = characters;
	}

	@Override
	public String toString() {
		return "ReqHangul [characters=" + characters + "]";
	}
	
}
