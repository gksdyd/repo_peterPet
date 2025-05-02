package com.peterpet.demo.module.payment;

import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao {

	public int insert(PaymentDto dto);
}
