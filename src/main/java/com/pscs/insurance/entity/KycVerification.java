package com.pscs.insurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kyc_verifications")
public class KycVerification {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String userType; // INSURER / AGENT / CUSTOMER
private Long userId;
private String verificationType; // NIN, BVN, CAC
private String status; // VERIFIED / FAILED
private String remarks;
}