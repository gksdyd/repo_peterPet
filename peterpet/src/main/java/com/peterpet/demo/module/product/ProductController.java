package com.peterpet.demo.module.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/xdm/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@ResponseBody
	@RequestMapping(value = "/ProductXdmProc")
	public Map<String, Object> productXdmProc(ProductVo vo) {
		Map<String, Object> rtType = new HashMap<String, Object>();

		vo.prodFuncSelect(productService.selectOneFunc(vo));
		rtType.put("funcArr", ProductVo.prodFuncArr);
		rtType.put("funcNameArr", ProductVo.prodFuncNameArr);			
		return rtType;
	}
	
	@RequestMapping(value = "/ProductXdmList")
	public String productXdmList(Model model, ProductVo vo) {
		vo.InitProdType();
		vo.setParamsPaging(productService.selectOneCount(vo));
		model.addAttribute("list", productService.selectList(vo));
		model.addAttribute("vo", vo);
		return "xdm/product/ProductXdmList";
	}

	@RequestMapping(value = "/xdm/product/ProductXdmForm")
	public String productXdmForm(Model model, ProductVo vo) {
		vo.InitProdType();
		if (productService.selectMaxSeq() == null) {
			vo.setProdSeq("1");
		} else {
			vo.setProdSeq((productService.selectMaxSeq() + 1) + "");
		}
//		vo.prodFuncSelect();
		model.addAttribute("item", vo);
//		model.addAttribute("func", vo);
//		model.addAttribute("info", feedInfoVo);
		return "xdm/product/ProductXdmForm";
	}

	@RequestMapping(value = "/xdm/product/ProductXdmInst")
	public String codeGroupXdmInst(ProductVo vo, ProductDto productDto) {
		vo.prodFuncRegister(productDto);
		productService.insert(productDto);
		return "redirect:/xdm/product/ProductXdmList";
	}
}
