package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ContractVerificationResult {
    private String repertoireContact;
    private List<Boolean> installmentPaid;

    public ContractVerificationResult(String repertoireContact, List<Boolean> installmentPaid) {
        this.repertoireContact = repertoireContact;
        this.installmentPaid = installmentPaid;
    }

    @JsonProperty("repertoireContact")
    public String getRepertoireContact() {
        return repertoireContact;
    }

    public void setRepertoireContact(String repertoireContact) {
        this.repertoireContact = repertoireContact;
    }

    @JsonProperty("installmentPaid")
    public List<Boolean> getInstallmentPaid() {
        return installmentPaid;
    }

    public void setInstallmentPaid(List<Boolean> installmentPaid) {
        this.installmentPaid = installmentPaid;
    }

}

