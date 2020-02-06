package com.boris.demo.riskengine.repository;

import com.boris.demo.riskengine.enums.PaymentStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int updatePayment(UUID paymentId, PaymentStatusEnum from, PaymentStatusEnum to){
        return jdbcTemplate.update( "UPDATE payment SET payment_status=? WHERE id=? AND payment_status=?", to.toString(), paymentId, from.toString() );
    }

}
