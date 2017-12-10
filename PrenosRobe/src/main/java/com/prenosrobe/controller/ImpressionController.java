package com.prenosrobe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prenosrobe.data.Impression;
import com.prenosrobe.service.ImpressionService;

@RestController
public class ImpressionController
{
	@Autowired
	private ImpressionService impressionService = ImpressionService.getInstance();
	
	@RequestMapping(value = "/impressions", method = RequestMethod.POST)
	public void sendImpression(@RequestBody Impression impression)
	{
		impressionService.sendImpression(impression);
	}
	
	@RequestMapping(value = "/impressions/{id}", method = RequestMethod.GET)
	public ResponseEntity<Impression> getImpressionByImpressionId(@PathVariable Long id)
	{
		return new ResponseEntity<>(impressionService.getImpressionByImpressionId(id.intValue()), HttpStatus.OK);
	}
}
