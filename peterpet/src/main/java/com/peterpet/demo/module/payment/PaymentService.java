package com.peterpet.demo.module.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	@Autowired
	PaymentDao paymentDao;
	
	public int insert(PaymentDto dto) {
		return paymentDao.insert(dto);
	}
	
	public List<PaymentDto> selectList(PaymentVo vo) {
		return paymentDao.selectList(vo);
	}
	
	public int selectCount(PaymentVo vo) {
		return paymentDao.selectCount(vo);
	}
}
