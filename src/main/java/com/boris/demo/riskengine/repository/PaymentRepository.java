package com.boris.demo.riskengine.repository;

import com.boris.demo.riskengine.enums.PaymentStatusEnum;

import java.util.UUID;

public interface PaymentRepository {
    int updatePayment(UUID paymentId, PaymentStatusEnum from, PaymentStatusEnum to);
}
