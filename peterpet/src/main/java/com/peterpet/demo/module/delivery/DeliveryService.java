package com.peterpet.demo.module.delivery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peterpet.demo.module.base.BaseService;

@Service
public class DeliveryService extends BaseService {

	@Autowired
	DeliveryDao deliveryDao;
	
	public List<DeliveryDto> selectList(DeliveryVo vo) {
		return deliveryDao.selectList(vo);
	}
}
