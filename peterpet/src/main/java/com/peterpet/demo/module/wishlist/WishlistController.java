package com.peterpet.demo.module.wishlist;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.module.base.BaseController;

@Controller
@RequestMapping(value = "/peter/wishlist")
public class WishlistController extends BaseController {

	@Autowired
	WishlistService wishlistService;
	
	@ResponseBody
	@RequestMapping(value = "/WishlistPeterProc")
	public Map<String, Object> wishlistPeterProc(WishlistVo vo) throws Exception {
		Map<String, Object> rtMap = new HashMap<>();
		
		if (vo.getUserSeq().equals("")) {
			rtMap.put("result", "fail");
		} else {
			wishlistService.insert(vo);
			rtMap.put("result", "success");
		}
		return rtMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/WishlistPeterDeltProc")
	public Map<String, Object> wishlistPeterDeltProc(WishlistVo vo) throws Exception {
		Map<String, Object> rtMap = new HashMap<>();
		wishlistService.uelete(vo);
		rtMap.put("result", "success");
		return rtMap;
	}
}
