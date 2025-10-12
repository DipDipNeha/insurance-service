package com.pscs.insurance.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "claims")
public class Claim {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Long customerId;
private String policyNumber;
private Double amount;
private String status; // FILED, APPROVED, REJECTED, PAID
private String remarks;
private String createdBy;
private String updatedBy;
private Date createdDate;
private Date updatedDate;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getCustomerId() {
	return customerId;
}
public void setCustomerId(Long customerId) {
	this.customerId = customerId;
}
public String getPolicyNumber() {
	return policyNumber;
}
public void setPolicyNumber(String policyNumber) {
	this.policyNumber = policyNumber;
}
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
public String getUpdatedBy() {
	return updatedBy;
}
public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
}


public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
public Date getUpdatedDate() {
	return updatedDate;
}
public void setUpdatedDate(Date updatedDate) {
	this.updatedDate = updatedDate;
}
@Override
public String toString() {
	return "Claim [id=" + id + ", customerId=" + customerId + ", policyNumber=" + policyNumber + ", amount=" + amount
			+ ", status=" + status + ", remarks=" + remarks + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
			+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
}





}