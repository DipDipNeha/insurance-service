package com.pscs.insurance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pscs.insurance.model.RequestData;

@RestController
@RequestMapping("/api/agent")
public class AgentDashboardController {

    // 1. Home Dashboard
    @GetMapping("/dashboard")
    public ResponseEntity<?> getAgentDashboardSummary(@RequestBody RequestData requestData) {
        // Returns sales performance, commissions earned, recent transactions
        return ResponseEntity.ok().build();
    }

    // 2. Sell Insurance
    @PostMapping("/insurance/sell")
    public ResponseEntity<?> sellInsurance(@RequestBody RequestData requestData) {
        // Onboard new customer, collect payment, issue policy
        return ResponseEntity.ok().build();
    }

    // 3. Customers
    @GetMapping("/customers")
    public ResponseEntity<?> getAgentCustomers(@RequestBody RequestData requestData) {
        // Returns list of customers registered under agent
        return ResponseEntity.ok().build();
    }

    // 4. Commissions & Wallet
    @GetMapping("/commissions")
    public ResponseEntity<?> getCommissionDetails(@RequestBody RequestData requestData) {
        // Returns earnings, bonuses, withdrawals
        return ResponseEntity.ok().build();
    }

    @PostMapping("/wallet/request-payout")
    public ResponseEntity<?> requestPayout(@RequestBody RequestData requestData) {
        return ResponseEntity.ok().build();
    }

    // 5. Products Library
    @GetMapping("/products")
    public ResponseEntity<?> getAvailableInsuranceProducts(@RequestBody RequestData requestData) {
        // Returns list of insurer products available to the agent
        return ResponseEntity.ok().build();
    }

    // 6. Reports
    @GetMapping("/reports")
    public ResponseEntity<?> getSalesAndPayoutReports(@RequestBody RequestData requestData ) {
        return ResponseEntity.ok().build();
    }

    // 7. Training & Support
    @GetMapping("/support/training-materials")
    public ResponseEntity<?> getTrainingMaterials(@RequestBody RequestData requestData) {
        // Access ConverConnect Academy content or FAQs
        return ResponseEntity.ok().build();
    }

    @GetMapping("/support/faqs")
    public ResponseEntity<?> getFAQs(@RequestBody RequestData requestData) {
        return ResponseEntity.ok().build();
    }

}
