package com.peterpet.demo.module.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peterpet.demo.module.base.BaseService;

@Service
public class WishlistService extends BaseService {

	@Autowired
	WishlistDao wishlistDao;
	
	public int insert(WishlistVo vo) {
		return wishlistDao.insert(vo);
	}
	
	public int uelete(WishlistVo vo) {
		return wishlistDao.uelete(vo);
	}
}
