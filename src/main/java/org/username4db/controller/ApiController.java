package org.username4db.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.username4db.dto.api.ReqDTO;
import org.username4db.dto.api.ResDTO;
import org.username4db.entity.Rec;
import org.username4db.repository.RecordRepo;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RecordRepo recordRepo;

	@Operation(method = "POST", summary = " @Operation summary", description = " @Operation description")
	@RequestMapping(value = "/save/{key}" //
			, method = RequestMethod.POST //
			, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE } //
			, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE })
	@ResponseBody
	ResDTO doSave(@PathVariable String key, @RequestBody ReqDTO req) {
		// recordRepo.deleteAll();
		Rec rec = new Rec();
		rec.setKey(req.getKey());
		rec.setValue(req.getValue());
		recordRepo.save(rec);
		ResDTO res = new ResDTO();
		res.setKey(key);
		res.setValue(TimeZone.getDefault().getDisplayName());
		res.setDecimal(BigDecimal.TEN);
		res.setServerDatetime(LocalDateTime.now());
		res.setClientDatetime(LocalDateTime.now());
		LOGGER.debug(res.toString());
		return res;
	}
}
