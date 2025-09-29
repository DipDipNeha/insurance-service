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
import com.pscs.insurance.services.InsuranceService;

/**
 * @author Dipak Kumar
 */
@CrossOrigin
@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {

	private final InsuranceService insuranceService;
	
	
	
	public InsuranceController(InsuranceService insuranceService) {
		this.insuranceService = insuranceService;
	}

	@GetMapping("/echo")
	public String echo() {
		return "Insurance Service is up and running!";
	}

	@PostMapping("/fetchpolicies")
	public ResponseEntity<ResponseData> fetchPolicies(@RequestBody RequestData userRequest) {

		ResponseData responseData = new ResponseData();

		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

	@PostMapping("/purchasepolicy")
	public ResponseEntity<ResponseData> purchasePolicy(@RequestBody RequestData userRequest) {
		ResponseData responseData = new ResponseData();

		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

	@PostMapping("/getpolicydetails")
	public ResponseEntity<ResponseData> getPolicyDetails(@RequestBody RequestData userRequest) {
		ResponseData responseData = new ResponseData();

		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

	@PostMapping("/claimpolicy")
	public ResponseEntity<ResponseData> claimPolicy(@RequestBody RequestData userRequest) {
		ResponseData responseData = new ResponseData();

		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

	@PostMapping("/renewpolicy")
	public ResponseEntity<ResponseData> renewPolicy(@RequestBody RequestData userRequest) {
		ResponseData responseData = new ResponseData();

		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

	@PostMapping("/cancelpolicy")
	public ResponseEntity<ResponseData> cancelPolicy(@RequestBody RequestData userRequest) {
		ResponseData responseData = new ResponseData();

		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

	@PostMapping("/submitclaim")
	public ResponseEntity<ResponseData> submitClaim(@RequestBody RequestData userRequest) {
		ResponseData responseData = new ResponseData();

		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

	@PostMapping("/trackclaim")
	public ResponseEntity<ResponseData> trackClaim(@RequestBody RequestData userRequest) {
		ResponseData responseData = new ResponseData();

		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

	@PostMapping("/enrollbeneficiary")
	public ResponseEntity<ResponseData> enrollBeneficiary(@RequestBody RequestData userRequest) {
		ResponseData responseData = new ResponseData();

		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

	@PostMapping("/grouppolicyenrollment")
	public ResponseEntity<ResponseData> groupPolicyEnrollment(@RequestBody RequestData userRequest) {
		ResponseData responseData = new ResponseData();

		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

}
