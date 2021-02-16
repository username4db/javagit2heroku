package org.username4db.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class HangulService {

	private final Map<String, Integer> mapInitial = new HashMap<>();

	private final Map<String, Integer> mapMedial = new HashMap<>();

	private final Map<String, Integer> mapFinal = new HashMap<>();

	private final Map<String, String> mapFinalComplex = new HashMap<>();

	private void initMapInitial() {
		mapInitial.put("ㄱ", 0);
		mapInitial.put("ㄲ", 1);
		mapInitial.put("ㄴ", 2);
		mapInitial.put("ㄷ", 3);
		mapInitial.put("ㄸ", 4);
		mapInitial.put("ㄹ", 5);
		mapInitial.put("ㅁ", 6);
		mapInitial.put("ㅂ", 7);
		mapInitial.put("ㅃ", 8);
		mapInitial.put("ㅅ", 9);
		mapInitial.put("ㅆ", 10);
		mapInitial.put("ㅇ", 11);
		mapInitial.put("ㅈ", 12);
		mapInitial.put("ㅉ", 13);
		mapInitial.put("ㅊ", 14);
		mapInitial.put("ㅋ", 15);
		mapInitial.put("ㅌ", 16);
		mapInitial.put("ㅍ", 17);
		mapInitial.put("ㅎ", 18);
	}

	private void initMapMedial() {
		mapMedial.put("ㅏ", 0);
		mapMedial.put("ㅐ", 1);
		mapMedial.put("ㅑ", 2);
		mapMedial.put("ㅒ", 3);
		mapMedial.put("ㅓ", 4);
		mapMedial.put("ㅔ", 5);
		mapMedial.put("ㅕ", 6);
		mapMedial.put("ㅖ", 7);
		mapMedial.put("ㅗ", 8);
		mapMedial.put("ㅘ", 9);
		mapMedial.put("ㅙ", 10);
		mapMedial.put("ㅚ", 11);
		mapMedial.put("ㅛ", 12);
		mapMedial.put("ㅜ", 13);
		mapMedial.put("ㅝ", 14);
		mapMedial.put("ㅞ", 15);
		mapMedial.put("ㅟ", 16);
		mapMedial.put("ㅠ", 17);
		mapMedial.put("ㅡ", 18);
		mapMedial.put("ㅢ", 19);
		mapMedial.put("ㅣ", 20);

	}

	private void initMapFinal() {
		mapFinal.put("", 0);
		mapFinal.put("ㄱ", 1);
		mapFinal.put("ㄲ", 2);
		mapFinal.put("ㄳ", 3);
		mapFinal.put("ㄴ", 4);
		mapFinal.put("ㄵ", 5);
		mapFinal.put("ㄶ", 6);
		mapFinal.put("ㄷ", 7);
		mapFinal.put("ㄹ", 8);
		mapFinal.put("ㄺ", 9);
		mapFinal.put("ㄻ", 10);
		mapFinal.put("ㄼ", 11);
		mapFinal.put("ㄽ", 12);
		mapFinal.put("ㄾ", 13);
		mapFinal.put("ㄿ", 14);
		mapFinal.put("ㅀ", 15);
		mapFinal.put("ㅁ", 16);
		mapFinal.put("ㅂ", 17);
		mapFinal.put("ㅄ", 18);
		mapFinal.put("ㅅ", 19);
		mapFinal.put("ㅆ", 20);
		mapFinal.put("ㅇ", 21);
		mapFinal.put("ㅈ", 22);
		mapFinal.put("ㅊ", 23);
		mapFinal.put("ㅋ", 24);
		mapFinal.put("ㅌ", 25);
		mapFinal.put("ㅍ", 26);
		mapFinal.put("ㅎ", 27);
	}

	private void initMapFinalComplex() {
		mapFinalComplex.put("ㄱㅅ", "ㄳ");
		mapFinalComplex.put("ㄴㅈ", "ㄵ");
		mapFinalComplex.put("ㄴㅎ", "ㄶ");
		mapFinalComplex.put("ㄹㄱ", "ㄺ");
		mapFinalComplex.put("ㄹㅁ", "ㄻ");
		mapFinalComplex.put("ㄹㅂ", "ㄼ");
		mapFinalComplex.put("ㄹㅅ", "ㄽ");
		mapFinalComplex.put("ㄹㅌ", "ㄾ");
		mapFinalComplex.put("ㄹㅍ", "ㄿ");
		mapFinalComplex.put("ㄹㅎ", "ㅀ");
		mapFinalComplex.put("ㅂㅅ", "ㅄ");
	}

	public HangulService() {
		super();
		initMapInitial();
		initMapMedial();
		initMapFinal();
		initMapFinalComplex();

	}

	public String syllable(String chars) {
		StringBuilder characters = new StringBuilder(chars);
		String sInitial = StringUtils.trimToNull(StringUtils.substring(characters.toString(), 0, 1));
		String sMedial = StringUtils.trimToNull(StringUtils.substring(characters.toString(), 1, 2));
		String sFinal = StringUtils.trimToNull(StringUtils.substring(characters.toString(), 2, 3));
		String sFinal2 = mapFinalComplex
				.get(StringUtils.trimToNull(StringUtils.substring(characters.toString(), 2, 4)));

		Integer iInitial = mapInitial.get(sInitial);
		Integer iMedial = mapMedial.get(sMedial);
		Integer iFinal = mapFinal.get(sFinal);
		Integer iFinal2 = mapFinal.get(sFinal2);

		if (sInitial == null || iInitial == null) {
			characters.setLength(0);
			return "";
		}

		if (sMedial == null || iMedial == null) {
			characters.setLength(1);
			return sInitial;
		}

		if (sFinal == null || iFinal == null) {
			characters.setLength(2);
			iFinal = 0;
		} else {
			if (iFinal2 == null || iFinal2 == iFinal) {
				characters.setLength(3);
			} else {
				characters.setLength(4);
				iFinal = iFinal2;
			}
		}

		int _result = ( //
		iInitial * 588 //
				+ iMedial * 28 //
				+ iFinal) //
				+ 44032;
		return Character.toString((char) _result);
	}
}
