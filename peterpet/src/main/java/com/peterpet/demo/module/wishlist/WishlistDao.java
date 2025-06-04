package com.peterpet.demo.module.wishlist;

import org.springframework.stereotype.Repository;

import com.peterpet.demo.module.base.BaseDao;
import com.peterpet.demo.module.member.MemberDto;

@Repository
public interface WishlistDao extends BaseDao {

	public int insert(WishlistVo vo);
	public int uelete(WishlistVo vo);
	public int count(MemberDto dto);
}
