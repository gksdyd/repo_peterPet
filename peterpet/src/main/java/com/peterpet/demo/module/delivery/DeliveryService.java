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
	
	public List<DeliveryDto> selectList(DeliveryDto dto) {
		return deliveryDao.selectList(dto);
	}
	
	public int insert(DeliveryDto dto) {
		return deliveryDao.insert(dto);
	}
	
	public int mainUpdate(DeliveryDto dto) {
		return deliveryDao.mainUpdate(dto);
	}
	
	public DeliveryDto selectOne(DeliveryVo vo) {
		return deliveryDao.selectOne(vo);
	}
	
	public int update(DeliveryDto dto) {
		if (deliveryDao.mainCheck(dto).getDeliMain() == 1) {
			dto.setDeliMain(1);
		} else if (dto.getDeliMain() == 1) {
			deliveryDao.mainUpdate(dto);
		}
		return deliveryDao.update(dto);
	}
	
	public int delete(DeliveryVo vo) {
		return deliveryDao.delete(vo);
	}
}
