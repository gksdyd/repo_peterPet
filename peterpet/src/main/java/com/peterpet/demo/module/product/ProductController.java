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
	public String productXdmList(Model model, FeedVo vo) {
		vo.InitProdType();
		vo.prodFuncSelect();
		vo.setParamsPaging(productService.selectOneCount(vo));
		model.addAttribute("list", productService.selectList(vo));
		model.addAttribute("vo", vo);
		return "xdm/product/ProductXdmList";
	}

	@RequestMapping(value = "/xdm/product/ProductXdmForm")
	public String productXdmForm(Model model, FeedVo feedFuncVo,
			ProductDto productDto, FeedInfoVo feedInfoVo) {
		feedFuncVo.InitProdType();
		if (productService.selectMaxSeq() == null) {
			feedFuncVo.setProdSeq("1");
		} else {
			feedFuncVo.setProdSeq((productService.selectMaxSeq() + 1) + "");
		}
		feedFuncVo.prodFuncSelect();
		model.addAttribute("item", productDto);
		model.addAttribute("func", feedFuncVo);
		model.addAttribute("info", feedInfoVo);
		return "xdm/product/ProductXdmForm";
	}

	@RequestMapping(value = "/xdm/product/ProductXdmInst")
	public String codeGroupXdmInst(FeedVo vo, ProductDto productDto) {
		vo.prodFuncRegister(productDto);
		productService.insert(productDto);
		return "redirect:/xdm/product/ProductXdmList";
	}
}
