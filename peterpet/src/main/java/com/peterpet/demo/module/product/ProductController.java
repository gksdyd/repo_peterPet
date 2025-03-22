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
	public String productXdmList(Model model, ProductVo vo) {
		vo.setParamsPaging(productService.selectOneCount());
		model.addAttribute("list", productService.selectList(vo));
		model.addAttribute("vo", vo);
		return "xdm/product/ProductXdmList";
	}

	@RequestMapping(value = "/xdm/product/ProductXdmForm")
	public String productXdmForm(Model model, ProductDto productDto) {
		if (productService.selectMaxSeq() == null) {
			productDto.setProdSeq("0");
		} else {
			productDto.setProdSeq(productService.selectMaxSeq());
		}
		productDto.feedFuncInit();
		model.addAttribute("item", productDto);
		return "xdm/product/ProductXdmForm";
	}

	@RequestMapping(value = "/xdm/product/ProductXdmInst")
	public String codeGroupXdmInst(ProductDto productDto) {
		productDto.setRegisterFlag(1);
		productDto.feedFuncInit();
		productService.insert(productDto);
		return "redirect:/xdm/product/ProductXdmList";
	}
}
