package com.peterpet.demo.module.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/xdm/product/ProductXdmList")
	public String productXdmList(Model model, ProductVo vo) {
		vo.InitProdType();
		vo.feedFuncSelect();
		vo.setParamsPaging(productService.selectOneCount(vo));
		model.addAttribute("list", productService.selectList(vo));
		model.addAttribute("vo", vo);
		return "xdm/product/ProductXdmList";
	}

	@RequestMapping(value = "/xdm/product/ProductXdmForm")
	public String productXdmForm(Model model, ProductDto productDto) {
		productDto.InitProdType();
		if (productService.selectMaxSeq() == null) {
			productDto.setProdSeq("0");
		} else {
			productDto.setProdSeq(productService.selectMaxSeq());
		}
		productDto.feedFuncSelect();
		model.addAttribute("item", productDto);
		return "xdm/product/ProductXdmForm";
	}

	@RequestMapping(value = "/xdm/product/ProductXdmInst")
	public String codeGroupXdmInst(ProductDto productDto) {
		productDto.setRegisterFlag(1);
		productDto.feedFuncRegister();
		productService.insert(productDto);
		return "redirect:/xdm/product/ProductXdmList";
	}
}
