package com.pscs.insurance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pscs.insurance.model.RequestData;

@RestController
@RequestMapping("/api/customer")
public class CustomerDashboardController {

    // 1. My Dashboard
    @GetMapping("/dashboard")
    public ResponseEntity<?> getCustomerDashboardSummary(@RequestBody RequestData request) {
        // Returns active covers, upcoming payments, claim status
        return ResponseEntity.ok().build();
    }

    // 2. Buy Insurance
    @GetMapping("/insurance/products")
    public ResponseEntity<?> getRecommendedInsuranceProducts(@RequestBody RequestData request) {
        // Returns product catalog based on profile or activity
        return ResponseEntity.ok().build();
    }

    @PostMapping("/insurance/buy")
    public ResponseEntity<?> buyInsurance(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    // 3. My Policies
    @GetMapping("/policies")
    public ResponseEntity<?> getActivePolicies(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/policies/{policyId}/renew")
    public ResponseEntity<?> renewPolicy(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/policies/{policyId}/cancel")
    public ResponseEntity<?> cancelPolicy(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    // 4. Claims
    @PostMapping("/claims/file")
    public ResponseEntity<?> fileClaim(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/claims")
    public ResponseEntity<?> getClaimHistory(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/claims/{claimId}/upload")
    public ResponseEntity<?> uploadClaimDocument(@RequestBody RequestData request,
            @PathVariable Long claimId,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().build();
    }

    // 5. Wallet
    @GetMapping("/wallet/balance")
    public ResponseEntity<?> getWalletBalance(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wallet/transactions")
    public ResponseEntity<?> getWalletTransactions(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wallet/bonuses")
    public ResponseEntity<?> getWalletBonuses(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    // 6. Profile & KYC
    @GetMapping("/profile")
    public ResponseEntity<?> getCustomerProfile(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/profile/update")
    public ResponseEntity<?> updateCustomerProfile(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/profile/upload-id")
    public ResponseEntity<?> uploadID(@RequestParam("file") MultipartFile idDocument,@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    // 7. Support & Chatbot
    @GetMapping("/support/faq")
    public ResponseEntity<?> getFAQs(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/support/livechat")
    public ResponseEntity<?> initiateLiveChat(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/support/whatsapp")
    public ResponseEntity<?> getWhatsAppSupportLink(@RequestBody RequestData request) {
        return ResponseEntity.ok().build();
    }
}
