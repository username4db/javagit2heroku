package org.username4db.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.username4db.dto.ReqDTO;
import org.username4db.dto.ResDTO;
import org.username4db.entity.Rec;
import org.username4db.repository.RecordRepo;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

	@Autowired
	private RecordRepo recordRepo;

	@RequestMapping(value = "/save/{key}" //
			, method = RequestMethod.POST //
			, consumes = MediaType.APPLICATION_JSON_VALUE //
			, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResDTO doSave(@PathVariable String key, @RequestBody ReqDTO req) {
		Rec rec = new Rec();
		rec.setKey(key);
		rec.setValue(req.getValue());
		recordRepo.save(rec);
		ResDTO res = new ResDTO();
		res.setKey(key);
		res.setValue(req.getValue());
		res.setDecimal(BigDecimal.TEN);
		res.setDatetime(LocalDateTime.now());
		return res;
	}
}
