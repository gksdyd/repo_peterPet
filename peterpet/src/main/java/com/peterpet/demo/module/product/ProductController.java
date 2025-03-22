package com.peterpet.demo.module.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peterpet.demo.module.codegroup.CodeGroupDto;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/xdm/product/ProductXdmList")
	public String productXdmList() {		
		return "xdm/product/ProductXdmList";
	}
	
	@RequestMapping(value = "/xdm/product/ProductXdmForm")
	public String productXdmForm(Model model, ProductVo productVo) {
		if (productService.selectMaxSeq() == null)
		{
			productVo.setProdSeq("0");
		} else {
			productVo.setProdSeq(productService.selectMaxSeq());					
		}
		productVo.feedFuncInit();
		model.addAttribute("item", productVo);
		return "xdm/product/ProductXdmForm";
	}
	
	@RequestMapping(value = "/xdm/product/ProductXdmInst")
	public String codeGroupXdmInst(ProductVo productVo) {
		productVo.setRegisterFlag(1);
		productVo.feedFuncInit();
		productService.insert(productVo);
		return "redirect:/xdm/product/ProductXdmList";
	}
}
