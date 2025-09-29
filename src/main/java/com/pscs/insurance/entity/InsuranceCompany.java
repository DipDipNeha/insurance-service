package com.pscs.insurance.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "insurance_company")
public class InsuranceCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id", unique = true, nullable = false)
    private String companyId;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "tax_id")
    private String taxId;

    @Column(name = "country")
    private String country;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "primary_contact_name")
    private String primaryContactName;

    @Column(name = "primary_contact_designation")
    private String primaryContactDesignation;

    @Column(name = "primary_contact_email")
    private String primaryContactEmail;

    @Column(name = "primary_contact_phone")
    private String primaryContactPhone;

    @Column(name = "support_email")
    private String supportEmail;

    @Column(name = "support_phone")
    private String supportPhone;

    @Column(name = "business_type")
    private String businessType;

    @Column(name = "products_offered", length = 1000)
    private String productsOffered;

    @Column(name = "branches_count")
    private Integer branchesCount;

    @Column(name = "employees_count")
    private Integer employeesCount;

    @Column(name = "irda_license_expiry")
    private String irdaLicenseExpiry;

    @Column(name = "irda_certificate_id")
    private String irdaCertificateId;

    @Column(name = "tax_certificate_id")
    private String taxCertificateId;

    @Column(name = "currency")
    private String currency;

    @Column(name = "time_zone")
    private String timeZone;

    @Column(name = "preferred_payment_modes", length = 500)
    private String preferredPaymentModes;

    @Column(name = "notification_email")
    private Boolean notificationEmail;

    @Column(name = "notification_sms")
    private Boolean notificationSms;

    @Column(name = "notification_whatsapp")
    private Boolean notificationWhatsapp;

    @Column(name = "status")
    private String status;

    @Column(name = "onboarding_date")
    private LocalDate onboardingDate;

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPrimaryContactName() {
        return primaryContactName;
    }

    public void setPrimaryContactName(String primaryContactName) {
        this.primaryContactName = primaryContactName;
    }

    public String getPrimaryContactDesignation() {
        return primaryContactDesignation;
    }

    public void setPrimaryContactDesignation(String primaryContactDesignation) {
        this.primaryContactDesignation = primaryContactDesignation;
    }

    public String getPrimaryContactEmail() {
        return primaryContactEmail;
    }

    public void setPrimaryContactEmail(String primaryContactEmail) {
        this.primaryContactEmail = primaryContactEmail;
    }

    public String getPrimaryContactPhone() {
        return primaryContactPhone;
    }

    public void setPrimaryContactPhone(String primaryContactPhone) {
        this.primaryContactPhone = primaryContactPhone;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public String getSupportPhone() {
        return supportPhone;
    }

    public void setSupportPhone(String supportPhone) {
        this.supportPhone = supportPhone;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getProductsOffered() {
        return productsOffered;
    }

    public void setProductsOffered(String productsOffered) {
        this.productsOffered = productsOffered;
    }

    public Integer getBranchesCount() {
        return branchesCount;
    }

    public void setBranchesCount(Integer branchesCount) {
        this.branchesCount = branchesCount;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(Integer employeesCount) {
        this.employeesCount = employeesCount;
    }

    public String getIrdaLicenseExpiry() {
        return irdaLicenseExpiry;
    }

    public void setIrdaLicenseExpiry(String irdaLicenseExpiry) {
        this.irdaLicenseExpiry = irdaLicenseExpiry;
    }

    public String getIrdaCertificateId() {
        return irdaCertificateId;
    }

    public void setIrdaCertificateId(String irdaCertificateId) {
        this.irdaCertificateId = irdaCertificateId;
    }

    public String getTaxCertificateId() {
        return taxCertificateId;
    }

    public void setTaxCertificateId(String taxCertificateId) {
        this.taxCertificateId = taxCertificateId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getPreferredPaymentModes() {
        return preferredPaymentModes;
    }

    public void setPreferredPaymentModes(String preferredPaymentModes) {
        this.preferredPaymentModes = preferredPaymentModes;
    }

    public Boolean getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(Boolean notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public Boolean getNotificationSms() {
        return notificationSms;
    }

    public void setNotificationSms(Boolean notificationSms) {
        this.notificationSms = notificationSms;
    }

    public Boolean getNotificationWhatsapp() {
        return notificationWhatsapp;
    }

    public void setNotificationWhatsapp(Boolean notificationWhatsapp) {
        this.notificationWhatsapp = notificationWhatsapp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOnboardingDate() {
        return onboardingDate;
    }

    public void setOnboardingDate(LocalDate onboardingDate) {
        this.onboardingDate = onboardingDate;
    }

	@Override
	public String toString() {
		return "InsuranceCompany [id=" + id + ", companyId=" + companyId + ", companyName=" + companyName
				+ ", registrationNumber=" + registrationNumber + ", licenseNumber=" + licenseNumber + ", taxId=" + taxId
				+ ", country=" + country + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + ", primaryContactName=" + primaryContactName
				+ ", primaryContactDesignation=" + primaryContactDesignation + ", primaryContactEmail="
				+ primaryContactEmail + ", primaryContactPhone=" + primaryContactPhone + ", supportEmail="
				+ supportEmail + ", supportPhone=" + supportPhone + ", businessType=" + businessType
				+ ", productsOffered=" + productsOffered + ", branchesCount=" + branchesCount + ", employeesCount="
				+ employeesCount + ", irdaLicenseExpiry=" + irdaLicenseExpiry + ", irdaCertificateId="
				+ irdaCertificateId + ", taxCertificateId=" + taxCertificateId + ", currency=" + currency
				+ ", timeZone=" + timeZone + ", preferredPaymentModes=" + preferredPaymentModes + ", notificationEmail="
				+ notificationEmail + ", notificationSms=" + notificationSms + ", notificationWhatsapp="
				+ notificationWhatsapp + ", status=" + status + ", onboardingDate=" + onboardingDate + "]";
	}

	
    
}
