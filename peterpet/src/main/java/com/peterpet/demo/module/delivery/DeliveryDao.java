package com.peterpet.demo.module.delivery;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.peterpet.demo.module.base.BaseDao;
import com.peterpet.demo.module.product.ProductVo;

@Repository
public interface DeliveryDao extends BaseDao {

	public List<DeliveryDto> selectList(DeliveryVo vo);
	public List<DeliveryDto> selectList(DeliveryDto dto);
	public List<DeliveryDto> selectList(ProductVo vo);
	public int insert(DeliveryDto dto);
	public int mainUpdate(DeliveryDto dto);
	public DeliveryDto selectOne(DeliveryVo vo);
	public int update(DeliveryDto dto);
	public DeliveryDto mainCheck(DeliveryDto dto);
	public int delete(DeliveryVo vo);
	public DeliveryDto selectMain(ProductVo vo);
}
