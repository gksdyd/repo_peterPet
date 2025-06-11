package com.peterpet.demo.module.wishlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.module.base.BaseController;
import com.peterpet.demo.module.product.ProductDto;
import com.peterpet.demo.module.product.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/peter/wishlist")
public class WishlistController extends BaseController {

	@Autowired
	WishlistService wishlistService;
	
	@Autowired
	ProductService productService;
	
	@ResponseBody
	@RequestMapping(value = "/WishlistPeterProc")
	public Map<String, Object> wishlistPeterProc(WishlistVo vo, HttpSession httpSession) throws Exception {
		Map<String, Object> rtMap = new HashMap<>();
		
		if (vo.getUserSeq().equals("")) {
			rtMap.put("result", "fail");
		} else {
			wishlistService.insert(vo);
			httpSession.setAttribute("sessWishPeter", (Integer) httpSession.getAttribute("sessWishPeter") + 1);
			rtMap.put("result", "success");
			rtMap.put("count", httpSession.getAttribute("sessWishPeter"));
		}
		return rtMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/WishlistPeterDeltProc")
	public Map<String, Object> wishlistPeterDeltProc(WishlistVo vo, HttpSession httpSession) throws Exception {
		Map<String, Object> rtMap = new HashMap<>();
		wishlistService.uelete(vo);
		httpSession.setAttribute("sessWishPeter", (Integer) httpSession.getAttribute("sessWishPeter") - 1);
		rtMap.put("result", "success");
		rtMap.put("count", httpSession.getAttribute("sessWishPeter"));
		return rtMap;
	}
	
	@RequestMapping(value = "/WishlistMypagePeterDeltProc")
	public String wishlistMypagePeterDeltProc(WishlistVo vo, Model model, HttpSession httpSession) throws Exception {
		wishlistService.uelete(vo);
		List<ProductDto> dtos = productService.selectWishlists(vo);
		for (int i = 0; i < dtos.size(); i++) {
			dtos.get(i).calculatePrice();
		}
		model.addAttribute("wishlists", dtos);
		httpSession.setAttribute("sessWishPeter", (Integer) httpSession.getAttribute("sessWishPeter") - 1);
		return "/peter/include/wishlist";
	}
	
	@RequestMapping(value = "/CartPeterProc")
	public String cartPeterProc(WishlistVo vo, Model model, HttpSession httpSession) throws Exception {
		if (vo.getProducts() != null) {
			int totalPrice = 0;
			List<ProductDto> dtos = productService.selectCart(vo);
			for (int i = 0; i < dtos.size(); i++) {
				dtos.get(i).setInfoCount(vo.getCounts().get(i));
				dtos.get(i).setInfoWeight(vo.getWeights().get(i));
				dtos.get(i).setInfoPrice(vo.getPrices().get(i) * vo.getCounts().get(i));
				totalPrice += dtos.get(i).getInfoPrice();
			}
			httpSession.setAttribute("sessCartPeter", dtos.size());
			model.addAttribute("totalPrice", totalPrice + 2500);
			model.addAttribute("cart", dtos);			
		} else {
			model.addAttribute("cart", null);
		}
		return "peter/include/cartContents";
	}
}
