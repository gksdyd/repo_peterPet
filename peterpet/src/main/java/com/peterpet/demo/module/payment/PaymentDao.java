package com.peterpet.demo.module.payment;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao {

	public int insert(PaymentDto dto);
	public List<PaymentDto> selectList(PaymentVo vo);
	public int selectCount(PaymentVo vo);
}
