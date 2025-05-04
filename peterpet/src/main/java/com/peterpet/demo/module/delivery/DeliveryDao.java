package com.peterpet.demo.module.delivery;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.peterpet.demo.module.base.BaseDao;

@Repository
public interface DeliveryDao extends BaseDao {

	public List<DeliveryDto> selectList(DeliveryVo vo);
}
