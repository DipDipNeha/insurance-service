package com.pscs.insurance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pscs.insurance.model.RequestData;
import com.pscs.insurance.model.ResponseData;
import com.pscs.insurance.services.CoverConnectService;

/**
 * @author Dipak Kumar
 */
@RestController
@RequestMapping("/api/coverconnect")
public class CoverConnectController {
	private final CoverConnectService coverConnectService;
	

    public CoverConnectController(CoverConnectService coverConnectService) {
		this.coverConnectService = coverConnectService;
	}
    @GetMapping("/echo")
	public String echo() {
		return "CoverConnect Service is up and running!";
	}
    
    
	@PostMapping("/registerpartner")
    public ResponseEntity<ResponseData> onboard(@RequestBody RequestData request) {
    	
		ResponseData registerResponse = coverConnectService.registerPartner(request);
    	
        return new ResponseEntity<>(registerResponse,HttpStatus.CREATED);
    }
	@PostMapping("/registerlistener")
  public ResponseEntity<ResponseData> registerListener(@RequestBody RequestData request) {

		ResponseData registerResponse = coverConnectService.registerListener(request);

		return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
	}
    @PostMapping("/login")
	public ResponseEntity<ResponseData> login(@RequestBody RequestData request) {
    	
    	        ResponseData registerResponse = coverConnectService.login(request);
		return new ResponseEntity<>(registerResponse, HttpStatus.OK);
	}
}
