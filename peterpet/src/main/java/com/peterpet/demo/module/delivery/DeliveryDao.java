package com.peterpet.demo.module.delivery;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.peterpet.demo.module.base.BaseDao;

@Repository
public interface DeliveryDao extends BaseDao {

	public List<DeliveryDto> selectList(DeliveryVo vo);
	public List<DeliveryDto> selectList(DeliveryDto dto);
	public int insert(DeliveryDto dto);
	public int mainUpdate(DeliveryDto dto);
	public DeliveryDto selectOne(DeliveryVo vo);
	public int update(DeliveryDto dto);
	public DeliveryDto mainCheck(DeliveryDto dto);
}
