package com.pscs.insurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Partner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String partnerName;
	private String partnerCode; 
	private String partnerShortCode;
	private String partnerCategory;
	private String partnerContact;
	private String contactEmail;
	
	private String type;
	private String status;
	private String country;
	private String regulatoryLicense;
	private String taxId;
	private String ekycNumber;
	private String ekycExpiryDate;
	private String ekycDocumentType;
	private String website;
	private String supportContact;
	private String supportEmail;
	private String integrationType; 
	private String remarks;
	private String channelType;
	private String channelName;
	private String channelCode;
	private String address;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegulatoryLicense() {
		return regulatoryLicense;
	}
	public void setRegulatoryLicense(String regulatoryLicense) {
		this.regulatoryLicense = regulatoryLicense;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getEkycNumber() {
		return ekycNumber;
	}
	public void setEkycNumber(String ekycNumber) {
		this.ekycNumber = ekycNumber;
	}
	public String getEkycExpiryDate() {
		return ekycExpiryDate;
	}
	public void setEkycExpiryDate(String ekycExpiryDate) {
		this.ekycExpiryDate = ekycExpiryDate;
	}
	public String getEkycDocumentType() {
		return ekycDocumentType;
	}
	public void setEkycDocumentType(String ekycDocumentType) {
		this.ekycDocumentType = ekycDocumentType;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getSupportContact() {
		return supportContact;
	}
	public void setSupportContact(String supportContact) {
		this.supportContact = supportContact;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getIntegrationType() {
		return integrationType;
	}
	public void setIntegrationType(String integrationType) {
		this.integrationType = integrationType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getPartnerShortCode() {
		return partnerShortCode;
	}
	public void setPartnerShortCode(String partnerShortCode) {
		this.partnerShortCode = partnerShortCode;
	}
	public String getPartnerCategory() {
		return partnerCategory;
	}
	public void setPartnerCategory(String partnerCategory) {
		this.partnerCategory = partnerCategory;
	}
	public String getPartnerContact() {
		return partnerContact;
	}
	public void setPartnerContact(String partnerContact) {
		this.partnerContact = partnerContact;
	}
	public String getSupportEmail() {
		return supportEmail;
	}
	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}
	@Override
	public String toString() {
		return "Partner [id=" + id + ", partnerName=" + partnerName + ", partnerCode=" + partnerCode
				+ ", partnerShortCode=" + partnerShortCode + ", partnerCategory=" + partnerCategory
				+ ", partnerContact=" + partnerContact + ", contactEmail=" + contactEmail + ", type=" + type
				+ ", status=" + status + ", country=" + country + ", regulatoryLicense=" + regulatoryLicense
				+ ", taxId=" + taxId + ", ekycNumber=" + ekycNumber + ", ekycExpiryDate=" + ekycExpiryDate
				+ ", ekycDocumentType=" + ekycDocumentType + ", website=" + website + ", supportContact="
				+ supportContact + ", supportEmail=" + supportEmail + ", integrationType=" + integrationType
				+ ", remarks=" + remarks + ", channelType=" + channelType + ", channelName=" + channelName
				+ ", channelCode=" + channelCode + ", address=" + address + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ "]";
	}
	
	
	
}