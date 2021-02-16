package org.username4db.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.username4db.dto.hangul.ReqHangul;
import org.username4db.dto.hangul.ResHangul;
import org.username4db.service.HangulService;

@RestController
@RequestMapping(value = "/hangul")
public class HangulController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HangulService service;

	@RequestMapping(value = "/syllable" //
			, method = RequestMethod.POST //
			, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE } //
			, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE })
	@ResponseBody
	ResHangul doSyllable(@RequestBody ReqHangul req) {
		ResHangul res = new ResHangul();
		LOGGER.info(req.toString());
		res.setChracters(service.syllable(req.getCharacters()));
		LOGGER.info(res.toString());
		return null;
	}
}
