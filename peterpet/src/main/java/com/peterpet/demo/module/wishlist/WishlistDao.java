package com.peterpet.demo.module.wishlist;

import org.springframework.stereotype.Repository;

import com.peterpet.demo.module.base.BaseDao;

@Repository
public interface WishlistDao extends BaseDao {

	public int insert(WishlistVo vo);
	public int uelete(WishlistVo vo);
}
