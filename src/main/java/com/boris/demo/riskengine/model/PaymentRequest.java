package com.boris.demo.riskengine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {
    private UUID id;
    private BigDecimal amount;
    private Currency currency;
    private UUID userId;
    private UUID payeeId;
    private UUID paymentMethod;
    private Date creationDate;
}
