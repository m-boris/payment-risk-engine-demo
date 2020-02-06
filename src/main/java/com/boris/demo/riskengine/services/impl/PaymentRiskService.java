package com.boris.demo.riskengine.services.impl;

import com.boris.demo.riskengine.enums.PaymentStatusEnum;
import com.boris.demo.riskengine.model.PaymentRequest;
import com.boris.demo.riskengine.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentRiskService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    PaymentRepository paymentRepository;

    @KafkaListener(topics = "payment_topic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        log.info(cr.toString());
        String value = (String) cr.value();
        PaymentRequest paymentRequest = objectMapper.readValue( value, PaymentRequest.class );
        int risk = calculateRisk( paymentRequest );
        PaymentStatusEnum paymentStatusEnum = risk<70 ? PaymentStatusEnum.SUCCESS:PaymentStatusEnum.FAIL;
        int result = paymentRepository.updatePayment( paymentRequest.getId(), PaymentStatusEnum.PENDING, paymentStatusEnum );
        if ( result>0) {
            log.info("Update record for id: {} SUCCESS, set payment status {} ",  paymentRequest.getId(), paymentStatusEnum);
        }else {
            log.info("Update record for id: {} FAIL, set payment status",  paymentRequest.getId());
        }
    }

    private int calculateRisk( PaymentRequest pageRequest ){
        return RandomUtils.nextInt( 0, 100);
    }
}
