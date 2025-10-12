package com.pscs.insurance.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
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
    
    
	@PostMapping("/partnerregister")
    public ResponseEntity<ResponseData> partner(@RequestBody RequestData request) {
    	
		ResponseData registerResponse = coverConnectService.registerPartner(request);
    	
        return new ResponseEntity<>(registerResponse,HttpStatus.CREATED);
    }
	@PostMapping("/insurerregister")
  public ResponseEntity<ResponseData> insurer(@RequestBody RequestData request) {

		ResponseData registerResponse = coverConnectService.registerInsurer(request);

		return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
	}
	
	@PostMapping("/customerregister")
	public ResponseEntity<ResponseData> onboardCustomer(@RequestBody RequestData request) {
		ResponseData registerResponse = coverConnectService.onboardCustomer(request);

		return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
	}
	
	
    @PostMapping("/login")
	public ResponseEntity<ResponseData> login(@RequestBody RequestData request) {
    	
    	        ResponseData registerResponse = coverConnectService.login(request);
		return new ResponseEntity<>(registerResponse, HttpStatus.OK);
	}
    
    //generate otp
    @PostMapping("/generateotp")
    public ResponseEntity<ResponseData> generateOtp(@RequestBody RequestData request) {
    	
    	        ResponseData registerResponse = coverConnectService.generateOtp(request);
    	                return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }
   
    //get customer type
    @PostMapping("/getcustomertype")
    public ResponseEntity<ResponseData> getCustomerType(@RequestBody RequestData request) {

		ResponseData registerResponse = coverConnectService.getCustomerType(request);
		return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }
    //get IdType
    @PostMapping("/getidtype")
    public ResponseEntity<ResponseData> getIdType(@RequestBody RequestData request) {
    	        ResponseData registerResponse = coverConnectService.getIdType(request);
    	                return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    	                
    }
    
    
}
