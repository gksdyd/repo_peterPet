package com.peterpet.demo.module.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peterpet.demo.module.base.BaseService;
import com.peterpet.demo.module.member.MemberDto;

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
	
	public int count(MemberDto dto) {
		return wishlistDao.count(dto);
	}
}
