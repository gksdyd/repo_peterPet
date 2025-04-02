package com.peterpet.demo.module.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.module.base.BaseController;
import com.peterpet.demo.module.code.CodeService;
import com.peterpet.demo.module.util.UtilDateTime;

@Controller
@RequestMapping(value = "/xdm/product")
public class ProductController extends BaseController {

	@Autowired
	ProductService productService;

	@ResponseBody
	@RequestMapping(value = "/ProductXdmProc")
	public Map<String, Object> productXdmProc(ProductVo vo) throws Exception {
		Map<String, Object> rtType = new HashMap<String, Object>();
		
		rtType.put("funcSeq", vo.getProdFunction());
		rtType.put("funcName", CodeService.selectOneCachedCode(vo.getProdFunction()));		
		return rtType;
	}
	
	@RequestMapping(value = "/ProductXdmList")
	public String productXdmList(Model model, ProductVo vo) {
		initSearchTime(vo);
		vo.InitProdType();
		vo.setParamsPaging(productService.selectOneCount(vo));
		model.addAttribute("list", productService.selectList(vo));
		model.addAttribute("vo", vo);
		return "xdm/product/ProductXdmList";
	}

	@RequestMapping(value = "/ProductXdmForm")
	public String productXdmForm(Model model, ProductDto productDto, @ModelAttribute("vo") ProductVo vo) {
		vo.InitProdType();
		if (productService.selectMaxSeq() == null) {
			productDto.setProdSeq("1");
		} else {
			productDto.setProdSeq((productService.selectMaxSeq() + 1) + "");
		}
		model.addAttribute("item", productDto);
//		vo.prodFuncSelect();
//		model.addAttribute("func", vo);
//		model.addAttribute("info", feedInfoVo);
		return "xdm/product/ProductXdmForm";
	}

	@RequestMapping(value = "/xdm/product/ProductXdmInst")
	public String codeGroupXdmInst(ProductVo vo, ProductDto productDto) {
//		vo.prodFuncRegister(productDto);
		productService.insert(productDto);
		return "redirect:/xdm/product/ProductXdmList";
	}
}
