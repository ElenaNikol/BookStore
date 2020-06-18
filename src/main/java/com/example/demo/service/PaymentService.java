package com.example.demo.service;

import com.example.demo.model.dto.ChargeRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import javax.smartcardio.CardException;

public interface PaymentService {
    Charge pay(ChargeRequest chargeRequest) throws CardException, StripeException;
}

