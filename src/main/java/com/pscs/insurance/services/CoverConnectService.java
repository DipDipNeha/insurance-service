/**
 * 
 */
package com.pscs.insurance.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.pscp.insurance.helper.ConvertRequestUtils;
import com.pscp.insurance.helper.CoreConstant;
import com.pscs.insurance.entity.CoverConnectLogin;
import com.pscs.insurance.entity.Customer;
import com.pscs.insurance.entity.Insurer;
import com.pscs.insurance.entity.OtpDataTabl;
import com.pscs.insurance.entity.Partner;
import com.pscs.insurance.model.RequestData;
import com.pscs.insurance.model.ResponseData;
import com.pscs.insurance.repo.CoverConnectLoginRepo;
import com.pscs.insurance.repo.CustomerRepo;
import com.pscs.insurance.repo.InsurerCompanyRepo;
import com.pscs.insurance.repo.OtpDataTablRepo;
import com.pscs.insurance.repo.PartnerRepo;
import com.pscs.insurance.services.post.EmailAndSMSPostingService;
import com.pscs.insurance.utils.CommonUtils;

/**
 *  Author Dipak Kumar
 */
@Service
public class CoverConnectService {
	private static final Logger logger = Logger.getLogger(CoverConnectService.class);
	private final PartnerRepo partnerRepo;
	private final CoverConnectLoginRepo coverConnectLoginRepo;
	private final InsurerCompanyRepo insuranceCompanyRepo;
	private final CustomerRepo customerRepo;
	private final OtpDataTablRepo otpDataTablRepo;
	private final EmailAndSMSPostingService smsPostingService;
	

	public CoverConnectService(PartnerRepo partnerRepo, CoverConnectLoginRepo coverConnectLoginRepo, InsurerCompanyRepo insuranceCompanyRepo, CustomerRepo customerRepo, OtpDataTablRepo otpDataTablRepo, EmailAndSMSPostingService smsPostingService) {
		this.partnerRepo = partnerRepo;
		this.coverConnectLoginRepo = coverConnectLoginRepo;
		this.insuranceCompanyRepo = insuranceCompanyRepo;
		this.customerRepo = customerRepo;
		this.otpDataTablRepo = otpDataTablRepo;
		this.smsPostingService = smsPostingService;
	}
	

	public ResponseData registerPartner(RequestData request) {
		ResponseData response = new ResponseData();
		try {
			logger.info("Request : " + request);
			String jsonString = ConvertRequestUtils.getJsonString(request.getJbody());
			// Convert the request body to a JSON object
			JSONObject requestJson = new JSONObject(jsonString);
			logger.info("Request Body: " + requestJson.toString());
			Partner partner = new Partner();
			partner.setBusinessType(requestJson.getString("businessType"));
			partner.setCompanyName(requestJson.getString("companyName"));
			partner.setLocation(requestJson.getString("location"));
			partner.setContactName(requestJson.getString("contactName"));
			partner.setContactEmail(requestJson.getString("contactEmail"));
			partner.setContactPhone(requestJson.getString("contactPhone"));
			partner.setRegistrationId(requestJson.getString("registrationId"));
			partner.setRemarks(requestJson.optString("remarks", ""));
			partner.setStatus("ACTIVE");
			partner.setCreatedBy("SYSTEM");
			partner.setCreatedDate(new Date());
			

			CoverConnectLogin checkCustomerExist = coverConnectLoginRepo
					.findByUserName(requestJson.getString("contactEmail"));
			if (checkCustomerExist != null) {
				response.setResponseCode("01");
				response.setResponseMessage("Partner with this email already exists");
				return response;
			} else {
				Partner save = partnerRepo.save(partner);
				System.out.println("Saved Partner: " + save);
				if (save == null) {
					response.setResponseCode("01");
					response.setResponseMessage("Failed to register partner");
					return response;
				} else {
					
//					validate if login already exist
					CoverConnectLogin checkLoginExist = coverConnectLoginRepo
							.findByUserName(requestJson.getString("contactEmail"));
					if (checkLoginExist != null) {
						response.setResponseCode("01");
						response.setResponseMessage("Partner login with this email already exists");
						return response;
					}
					
					
					CoverConnectLogin login = new CoverConnectLogin();
					login.setUserName(requestJson.getString("contactEmail"));
					login.setPassword(requestJson.getString("password"));
					login.setPartnerCode(requestJson.getString("companyName"));
					login.setCustType("PATNER");
					login.setCustId(save.getId() + "");
					login.setStatus("ACTIVE");
					login.setCreatedBy("SYSTEM");
					login.setCreatedDate(new Date().toString());

					CoverConnectLogin saveLogin = coverConnectLoginRepo.save(login);
					if (saveLogin == null) {
						response.setResponseCode("01");
						response.setResponseMessage("Failed to register partner login");
						return response;
					}

					response.setResponseCode("00");
					response.setResponseMessage("Partner registered successfully");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setResponseCode("01");
			response.setResponseMessage("Failed to register partner");
			return response;
		}

		return response;
	}

	public ResponseData login(RequestData request) {
		ResponseData response = new ResponseData();
		try {
			logger.info("Request : " + request);
			String jsonString = ConvertRequestUtils.getJsonString(request.getJbody());
			JSONObject requestJson = new JSONObject(jsonString);
			logger.info("Request Body: " + requestJson.toString());
			String userid = requestJson.getString("email");
			String password = requestJson.getString("password");
			CoverConnectLogin byUsernameAndPassword = coverConnectLoginRepo.findByUserNameAndPassword(userid, password);

			if (byUsernameAndPassword == null) {
				response.setResponseCode("01");
				response.setResponseMessage("Invalid partner credentials");
				return response;
			} else {
				
				if(byUsernameAndPassword.getCustType().equals("PATNER")) {
					Partner partner = partnerRepo.findById(Long.parseLong(byUsernameAndPassword.getCustId()))
							.orElse(null);
					if (partner != null) {
						response.setResponseData(partner);
					}
				}else if(byUsernameAndPassword.getCustType().equals("INSURER")) {
                    Insurer insurer = insuranceCompanyRepo.findById(Long.parseLong(byUsernameAndPassword.getCustId()))
                            .orElse(null);
                    if (insurer != null) {
                        response.setResponseData(insurer);
                    }
				}
                    else if(byUsernameAndPassword.getCustType().equals("CUSTOMER")) {
                        Customer customer = customerRepo.findById(Long.parseLong(byUsernameAndPassword.getCustId()))
                                .orElse(null);
                        if (customer != null) {
                            response.setResponseData(customer);
                        }
                    }
                    
				
				
				response.setResponseCode("00");
				response.setResponseMessage("Partner login successful");
				response.setResponseData(byUsernameAndPassword);
			}
		} catch (Exception e) {
			response.setResponseCode("01");
			response.setResponseMessage("Failed to login partner");
			return response;
		}

		return response;
	}


	public ResponseData registerInsurer(RequestData request) {
		ResponseData response = new ResponseData();
		try {
			logger.info("Request : " + request);
			String jsonString = ConvertRequestUtils.getJsonString(request.getJbody());
			// Convert the request body to a JSON object
			JSONObject requestJson = new JSONObject(jsonString);
			logger.info("Request Body: " + requestJson.toString());
			Insurer insuranceCompany = new Insurer();
			insuranceCompany.setCompanyName(requestJson.getString("companyName"));
			insuranceCompany.setLicenseNumber(requestJson.getString("licenseNumber"));
			insuranceCompany.setContactPersonName(requestJson.getString("contactPersonName"));
			insuranceCompany.setContactEmail(requestJson.getString("contactEmail"));
			insuranceCompany.setContactPhone(requestJson.getString("contactPhone"));
			insuranceCompany.setHeadquartersAddress(requestJson.getString("headquartersAddress"));
			insuranceCompany.setIntegrationInterest(requestJson.getString("integrationInterest"));
			insuranceCompany.setRemarks(requestJson.optString("remarks", ""));
			
			insuranceCompany.setStatus("ACTIVE");
			
			
			//Validate if insurer already exist
			Insurer checkInsurerExist = insuranceCompanyRepo.findByContactEmail(requestJson.getString("contactEmail"));
			if (checkInsurerExist != null) {
				response.setResponseCode("01");
				response.setResponseMessage("Insurer with this email already exists");
				return response;
			}
			
			
			

			  Insurer save = insuranceCompanyRepo.save(insuranceCompany);
			System.out.println("Saved Insurance Company: " + save);
			if (save == null) {
				response.setResponseCode("01");
				response.setResponseMessage("Failed to register insurance company");
				return response;
			} else {
				response.setResponseCode("00");
				response.setResponseMessage("Insurance company registered successfully");
				
				
//				validate if login already exist
				CoverConnectLogin checkLoginExist = coverConnectLoginRepo
						.findByUserName(requestJson.getString("contactEmail"));
				if (checkLoginExist != null) {
					response.setResponseCode("01");
					response.setResponseMessage("Insurer login with this email already exists");
					return response;
				}
				CoverConnectLogin login = new CoverConnectLogin();
				
				login.setUserName(requestJson.getString("contactEmail"));
				login.setPassword(requestJson.getString("password"));
				login.setPartnerCode(requestJson.getString("companyName"));
				login.setCustType("INSURER");
				login.setCustId(save.getId() + "");
				login.setStatus("ACTIVE");
				login.setCreatedBy("SYSTEM");
				login.setCreatedDate(new Date().toString());
				
				CoverConnectLogin saveLogin = coverConnectLoginRepo.save(login);
				if (saveLogin == null) {
					response.setResponseCode("01");
					response.setResponseMessage("Failed to register insurer login");
					return response;
				}
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setResponseCode("01");
			response.setResponseMessage("Failed to register listener");
			return response;
		}
		
		
		return response;
	}


	public ResponseData onboardCustomer(RequestData request) {
		ResponseData response = new ResponseData();
		try {
			logger.info("Request : " + request);
			String jsonString = ConvertRequestUtils.getJsonString(request.getJbody());
			// Convert the request body to a JSON object
			JSONObject requestJson = new JSONObject(jsonString);
			logger.info("Request Body: " + requestJson.toString());
			Customer customer = new Customer();
			customer.setType(requestJson.getString("type"));
			customer.setName(requestJson.getString("name"));
			customer.setPhone(requestJson.getString("phone"));
			customer.setEmail(requestJson.getString("email"));
			customer.setIdNumber(requestJson.getString("idNumber"));
			customer.setDob(requestJson.getString("dob"));
			customer.setCompanyName(requestJson.optString("companyName", ""));
			customer.setWalletId(requestJson.getString("walletId"));
			customer.setIdType(requestJson.getString("idType"));
			customer.setStatus("ACTIVE");
			customer.setCreatedBy("SYSTEM");
			customer.setCreatedDate(new Date());
			Customer save = customerRepo.save(customer);
			System.out.println("Saved Customer: " + save);
			
			//Validate if customer already exist
			Customer checkCustomerExist = customerRepo.findByEmail(requestJson.getString("email"));
			if (checkCustomerExist == null) {
				response.setResponseCode("01");
				response.setResponseMessage("Customer with this email already exists");
				return response;
			}
			
			
			if (save == null) {
				response.setResponseCode("01");
				response.setResponseMessage("Failed to onboard customer");
				return response;
			}else {
				
				
				
				
				response.setResponseCode("00");
				response.setResponseMessage("Customer onboarded successfully");
				//Validate if login already exist
				CoverConnectLogin checkLoginExist = coverConnectLoginRepo
						.findByUserName(requestJson.getString("email"));
				if (checkLoginExist != null) {
					response.setResponseCode("01");
					response.setResponseMessage("Customer login with this email already exists");
					return response;
				}
				
				
				CoverConnectLogin login = new CoverConnectLogin();
				login.setUserName(requestJson.getString("email"));
				login.setPassword(requestJson.getString("password"));
				login.setPartnerCode(requestJson.getString("name"));
				login.setCustType("CUSTOMER");
				login.setCustId(save.getId() + "");
				login.setStatus("ACTIVE");
				login.setCreatedBy("SYSTEM");
				login.setCreatedDate(new Date().toString());
				CoverConnectLogin saveLogin = coverConnectLoginRepo.save(login);
					
				if (saveLogin == null) {
					response.setResponseCode("01");
					response.setResponseMessage("Failed to register customer login");
					return response;
				}
				
			}
			
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setResponseCode("01");
			response.setResponseMessage("Failed to onboard customer");
			return response;
		}
		return response;
	}


	public ResponseData generateOtp(RequestData request) {
		ResponseData response = new ResponseData();
		try {

			System.out.println("Request : " + request);
			String jsonString = ConvertRequestUtils.getJsonString(request.getJbody());
			String strJheader = ConvertRequestUtils.getJsonString(request.getJheader());
			JSONObject jHeader = new JSONObject(strJheader);

			JSONObject jsonObject = new JSONObject(jsonString);
			System.out.println("Request Body: " + jsonObject.toString());


			otpDataTablRepo.updateOtpStatusByUserId(jHeader.getString("userid"), "E");
			
			String otp = "221232" ;// CommonUtils.createRandomNumber(6);
			String encryptedOtp = CommonUtils.b64_sha256(otp);
			// generate otp by using random
			OtpDataTabl otpDataTabl = new OtpDataTabl();
			otpDataTabl.setOtp(encryptedOtp);
			otpDataTabl.setUserId(jHeader.getString("userid"));
			otpDataTabl.setTransType(jHeader.getString("requestType"));
			otpDataTabl.setMobileNo(jsonObject.getString("mobileNumber"));
			otpDataTabl.setEmailId(jsonObject.getString("email"));
			
			
			
		
			
			
			otpDataTabl.setChannel(jHeader.getString("channel"));
			otpDataTabl.setOtpStatus("A");

			OtpDataTabl save = otpDataTablRepo.save(otpDataTabl);

			if (save == null) {
				response.setResponseCode(CoreConstant.FAILURE_CODE);
				response.setResponseMessage(CoreConstant.FAILED + " to Generate OTP");
				return response;
			} else {
				JSONObject smsRequest = new JSONObject();
				if (jsonObject.getString("mobileNumber").startsWith("234")) {

					smsRequest.put("messages", ConvertRequestUtils.generateSMSJson(jsonObject.getString("mobileNumber"),
							jHeader.getString("requestType"), otp));

				} else {
					response.setResponseCode(CoreConstant.SUCCESS_CODE);
					response.setResponseMessage(CoreConstant.SMS_SENT + otp);
					return response;
				}

				response.setResponseMessage(CoreConstant.OTP_SENT + otp);
				response.setResponseCode(CoreConstant.SUCCESS_CODE);
				JSONObject sendSMSRes = smsPostingService.sendPostRequest(smsRequest.toString(),"sms.url");
				
				JSONObject emailRequest = new JSONObject();
				
				emailRequest.put("messages", ConvertRequestUtils.generateEmailJson(otpDataTabl.getEmailId(),
						"MoneyX One Time Password", "Dear Customer, Your OTP is " + otp));
				
				JSONObject sendMailRes = smsPostingService.sendPostRequest(emailRequest.toString(),"email.url");

				if (sendSMSRes.getString("respsode").equals("200")) {
					response.setResponseCode(CoreConstant.SUCCESS_CODE);
					response.setResponseMessage(CoreConstant.SMS_SENT + otp);
					response.setResponseData(sendSMSRes.toMap());

				} else {
					response.setResponseCode(CoreConstant.FAILURE_CODE);
					response.setResponseMessage(CoreConstant.FAILED + " to send SMS");
					response.setResponseData(sendSMSRes.toMap());
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setResponseCode(CoreConstant.FAILURE_CODE);
			response.setResponseMessage(CoreConstant.FAILED );

		}
		return response;

	}

	// Validate otp
	public ResponseData validateOtp(RequestData request) {
		ResponseData response = new ResponseData();
		try {

			System.out.println("Request : " + request);
			String jsonString = ConvertRequestUtils.getJsonString(request.getJbody());
			String strJheader = ConvertRequestUtils.getJsonString(request.getJheader());
			JSONObject jHeader = new JSONObject(strJheader);
			JSONObject requestJson = new JSONObject(jsonString);
			System.out.println("Request Body: " + requestJson.toString());

			String otp = requestJson.getString("authValue");
			String username = jHeader.getString("userid");
			String mobileNumber = requestJson.getString("mobileNumber");

			OtpDataTabl otpDataTabl = otpDataTablRepo.findByMobileNoAndOtpAndOtpStatus(mobileNumber, CommonUtils.b64_sha256(otp),"A");

			// Check if the OTP is older than 2 minutes

			if (otpDataTabl != null) {
				long otpCreationTime = otpDataTabl.getTransDttm().getTime();
				long currentTime = System.currentTimeMillis();
				long timeDifference = currentTime - otpCreationTime;

				// Check if the OTP is older than 2 minutes (120000 milliseconds) use constant
				// for 2 minutes
				if (timeDifference > CommonUtils.OTP_VALIDITY_DURATION) {
					otpDataTabl.setOtpStatus("E"); // Set status to Expired
					otpDataTablRepo.save(otpDataTabl);
					response.setResponseCode(CoreConstant.FAILURE_CODE);
					response.setResponseMessage(CoreConstant.EXPIRED_OTP);
					return response;
				}
			}

			if (otpDataTabl == null) {
				response.setResponseCode(CoreConstant.FAILURE_CODE);
				response.setResponseMessage(CoreConstant.INVALID_OTP);
				return response;
			} else if (otpDataTabl.getOtpStatus().equals("A")) {
				response.setResponseCode(CoreConstant.SUCCESS_CODE);
				response.setResponseMessage(CoreConstant.OTP_VERIFIED);
				// if success the update the oto status to S
				otpDataTabl.setOtpStatus("S");
				otpDataTablRepo.save(otpDataTabl);

			} else if (otpDataTabl.getOtpStatus().equals("S")) {
				response.setResponseCode(CoreConstant.FAILURE_CODE);
				response.setResponseMessage(CoreConstant.OTP_USED);
			} else if (otpDataTabl.getOtpStatus().equals("E")) {
				response.setResponseCode(CoreConstant.FAILURE_CODE);
				response.setResponseMessage(CoreConstant.EXPIRED_OTP);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setResponseCode(CoreConstant.FAILURE_CODE);
			response.setResponseMessage(CoreConstant.FAILED + " to Validate OTP");
		}
		return response;
	}


	public ResponseData getCustomerType(RequestData request) {
		ResponseData response = new ResponseData();
		try {
			logger.info("Request : " + request);
			String jsonString = ConvertRequestUtils.getJsonString(request.getJbody());
			JSONObject requestJson = new JSONObject(jsonString);
			logger.info("Request Body: " + requestJson.toString());

			JSONArray validTypes = new JSONArray();
			// Hardcoded valid customer types
			validTypes.put("INDIVIDUAL");
			validTypes.put("CORPORATE");
			
            
			
				response.setResponseCode("00");
				response.setResponseMessage("Customer type retrieved successfully");
				response.setResponseData(validTypes.toList());
			
		} catch (Exception e) {
			response.setResponseCode("01");
			response.setResponseMessage("Failed to get customer type");
			return response;
		}
		
		return response;
	}


	public ResponseData getIdType(RequestData request) {
		ResponseData response = new ResponseData();
		try {
			logger.info("Request : " + request);
			String jsonString = ConvertRequestUtils.getJsonString(request.getJbody());
			JSONObject requestJson = new JSONObject(jsonString);
			logger.info("Request Body: " + requestJson.toString());

			JSONArray validIdTypes = new JSONArray();
			// Hardcoded valid ID types
			validIdTypes.put("PASSPORT");
			validIdTypes.put("DRIVER_LICENSE");
			validIdTypes.put("NATIONAL_ID");
			validIdTypes.put("VOTER_ID");
			validIdTypes.put("SSNIT_ID");
			validIdTypes.put("TAX_ID");
			validIdTypes.put("NIN");
			validIdTypes.put("BVN");
			validIdTypes.put("GHANA_CARD");

			response.setResponseCode("00");
			response.setResponseMessage("ID types retrieved successfully");
			response.setResponseData(validIdTypes.toList());

		} catch (Exception e) {
			response.setResponseCode("01");
			response.setResponseMessage("Failed to get ID types");
			return response;
		}
		return response;
	}
	
	


}
