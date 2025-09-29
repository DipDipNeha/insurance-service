/**
 * 
 */
package com.pscs.insurance.services;

import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.pscp.insurance.helper.ConvertRequestUtils;
import com.pscs.insurance.entity.CoverConnectLogin;
import com.pscs.insurance.entity.InsuranceCompany;
import com.pscs.insurance.entity.Partner;
import com.pscs.insurance.model.RequestData;
import com.pscs.insurance.model.ResponseData;
import com.pscs.insurance.repo.CoverConnectLoginRepo;
import com.pscs.insurance.repo.InsuranceCompanyRepo;
import com.pscs.insurance.repo.PartnerRepo;

/**
 *  Author Dipak Kumar
 */
@Service
public class CoverConnectService {
	private static final Logger logger = Logger.getLogger(CoverConnectService.class);
	private final PartnerRepo partnerRepo;
	private final CoverConnectLoginRepo coverConnectLoginRepo;
	private final InsuranceCompanyRepo insuranceCompanyRepo;

	public CoverConnectService(PartnerRepo partnerRepo, CoverConnectLoginRepo coverConnectLoginRepo, InsuranceCompanyRepo insuranceCompanyRepo) {
		this.partnerRepo = partnerRepo;
		this.coverConnectLoginRepo = coverConnectLoginRepo;
		this.insuranceCompanyRepo = insuranceCompanyRepo;
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
			partner.setPartnerName(requestJson.getString("partnerName"));
			partner.setContactEmail(requestJson.getString("partnerEmail"));
			partner.setPartnerContact(requestJson.getString("partnerPhone"));
			partner.setSupportContact(requestJson.getString("supportContact"));
			partner.setSupportEmail(requestJson.getString("supportEmail"));
			partner.setAddress(requestJson.getString("address"));
			partner.setSupportEmail(requestJson.getString("supportEmail"));
			partner.setPartnerCode(requestJson.getString("partnerCode"));
			partner.setPartnerShortCode(requestJson.getString("partnerShortCode"));
			partner.setPartnerCategory(requestJson.getString("partnerCategory"));
			partner.setChannelCode(requestJson.getString("channelCode"));
			partner.setChannelName(requestJson.getString("channelName"));
			partner.setTaxId(requestJson.getString("taxId"));
			partner.setRegulatoryLicense(requestJson.getString("regulatoryLicense"));
			partner.setEkycDocumentType(requestJson.getString("ekycDocumentType"));
			partner.setEkycExpiryDate(requestJson.getString("ekycExpiryDate"));
			partner.setEkycNumber(requestJson.getString("ekycNumber"));
			partner.setCountry(requestJson.getString("country"));
			partner.setWebsite(requestJson.getString("website"));
			partner.setIntegrationType(requestJson.getString("integrationType"));
			partner.setChannelType(requestJson.getString("channelType"));
			partner.setType(requestJson.getString("type"));

			partner.setStatus("ACTIVE");
			partner.setCreatedBy("SYSTEM");

			partner.setCreatedDate(new Date().toString());

			CoverConnectLogin checkCustomerExist = coverConnectLoginRepo
					.findByUserName(requestJson.getString("partnerEmail"));
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

					CoverConnectLogin login = new CoverConnectLogin();
					login.setUserName(requestJson.getString("partnerEmail"));
					login.setPassword(ConvertRequestUtils.generatePassword());
					login.setPartnerCode(requestJson.getString("partnerCode"));
					login.setCustType(requestJson.getString("type"));
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
			String userid = requestJson.getString("partnerEmail");
			String password = requestJson.getString("password");
			CoverConnectLogin byUsernameAndPassword = coverConnectLoginRepo.findByUserNameAndPassword(userid, password);

			if (byUsernameAndPassword == null) {
				response.setResponseCode("01");
				response.setResponseMessage("Invalid partner credentials");
				return response;
			} else {
				response.setResponseCode("00");
				response.setResponseMessage("Partner login successful");
			}
		} catch (Exception e) {
			response.setResponseCode("01");
			response.setResponseMessage("Failed to login partner");
			return response;
		}

		return response;
	}


	public ResponseData registerListener(RequestData request) {
		ResponseData response = new ResponseData();
		try {
			logger.info("Request : " + request);
			String jsonString = ConvertRequestUtils.getJsonString(request.getJbody());
			// Convert the request body to a JSON object
			JSONObject requestJson = new JSONObject(jsonString);
			logger.info("Request Body: " + requestJson.toString());
			InsuranceCompany insuranceCompany = new InsuranceCompany();
			
//			 "companyId": "INS-001",
//			  "companyName": "SecureLife Insurance Ltd.",
//			  "registrationNumber": "REG-987654321",
//			  "licenseNumber": "LIC-2025-1234",
//			  "taxId": "TAX-56789",
//			  "country": "India",
//			  "street": "12th Floor, Tower A, Cyber City",
//			  "city": "Gurgaon",
//			  "state": "Haryana",
//			  "postalCode": "122002",
//			  "primaryContactName": "Ramesh Kumar",
//			  "primaryContactDesignation": "Head of Operations",
//			  "primaryContactEmail": "ramesh.kumar@securelife.com",
//			  "primaryContactPhone": "+91-9876543210",
//			  "supportEmail": "support@securelife.com",
//			  "supportPhone": "+91-1800-123-456",
//			  "businessType": "Life Insurance",
//			  "productsOffered": "Term Insurance, Health Insurance, Motor Insurance, Travel Insurance",
//			  "branchesCount": 150,
//			  "employeesCount": 2000,
//			  "irdaLicenseExpiry": "2030-12-31",
//			  "irdaCertificateId": "DOC-IRDA-2025-01",
//			  "taxCertificateId": "DOC-TAX-2025-01",
//			  "currency": "INR",
//			  "timeZone": "Asia/Kolkata",
//			  "preferredPaymentModes": "NEFT, RTGS, UPI",
//			  "notificationEmail": true,
//			  "notificationSms": true,
//			  "notificationWhatsapp": false,
//			  "status": "ACTIVE",
//			  "onboardingDate": "2025-09-29"
			insuranceCompany.setCompanyId(requestJson.getString("companyId"));
			insuranceCompany.setCompanyName(requestJson.getString("companyName"));
			insuranceCompany.setRegistrationNumber(requestJson.getString("registrationNumber"));
			insuranceCompany.setLicenseNumber(requestJson.getString("licenseNumber"));
			insuranceCompany.setTaxId(requestJson.getString("taxId"));
			insuranceCompany.setCountry(requestJson.getString("country"));
			insuranceCompany.setStreet(requestJson.getString("street"));
			insuranceCompany.setCity(requestJson.getString("city"));
			insuranceCompany.setState(requestJson.getString("state"));
			insuranceCompany.setPostalCode(requestJson.getString("postalCode"));
			insuranceCompany.setPrimaryContactName(requestJson.getString("primaryContactName"));
			insuranceCompany.setPrimaryContactDesignation(requestJson.getString("primaryContactDesignation"));
			insuranceCompany.setPrimaryContactEmail(requestJson.getString("primaryContactEmail"));
			insuranceCompany.setPrimaryContactPhone(requestJson.getString("primaryContactPhone"));
			insuranceCompany.setSupportEmail(requestJson.getString("supportEmail"));
			insuranceCompany.setSupportPhone(requestJson.getString("supportPhone"));
			insuranceCompany.setBusinessType(requestJson.getString("businessType"));
			insuranceCompany.setProductsOffered(requestJson.getString("productsOffered"));
			insuranceCompany.setBranchesCount(requestJson.getInt("branchesCount"));
			insuranceCompany.setEmployeesCount(requestJson.getInt("employeesCount"));
			insuranceCompany.setIrdaLicenseExpiry(requestJson.getString("irdaLicenseExpiry"));
			insuranceCompany.setIrdaCertificateId(requestJson.getString("irdaCertificateId"));
			insuranceCompany.setTaxCertificateId(requestJson.getString("taxCertificateId"));
			insuranceCompany.setCurrency(requestJson.getString("currency"));
			insuranceCompany.setTimeZone(requestJson.getString("timeZone"));
			insuranceCompany.setPreferredPaymentModes(requestJson.getString("preferredPaymentModes"));
			insuranceCompany.setNotificationEmail(requestJson.getBoolean("notificationEmail"));
			insuranceCompany.setNotificationSms(requestJson.getBoolean("notificationSms"));
			insuranceCompany.setNotificationWhatsapp(requestJson.getBoolean("notificationWhatsapp"));
			insuranceCompany.setStatus("ACTIVE");
			
			

			InsuranceCompany save = insuranceCompanyRepo.save(insuranceCompany);
			System.out.println("Saved Insurance Company: " + save);
			if (save == null) {
				response.setResponseCode("01");
				response.setResponseMessage("Failed to register insurance company");
				return response;
			}
			
			// Placeholder response
			response.setResponseCode("00");
			response.setResponseMessage("Listener registered successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setResponseCode("01");
			response.setResponseMessage("Failed to register listener");
			return response;
		}
		
		
		return response;
	}

}
