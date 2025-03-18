package com.peterpet.demo.module.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/xdm/product/ProductXdmList")
	public String productXdmList(Model model, ProductDto productDto) {
		model.addAttribute("productType", productService.selectProductType());
		
		List<ProductDto> productCates = productService.selectProductCate();
		model.addAttribute("feedSalaryAge", productService.selectProductFilt(productCates.get(0)));
		model.addAttribute("feedType", productService.selectProductFilt(productCates.get(1)));
		model.addAttribute("feedEtc", productService.selectProductFilt(productCates.get(2)));
		model.addAttribute("feedSize", productService.selectProductFilt(productCates.get(3)));
		model.addAttribute("feedFunction", productService.selectProductFilt(productCates.get(4)));
		model.addAttribute("feedBrand", productService.selectProductFilt(productCates.get(5)));
		model.addAttribute("feedIngredient", productService.selectProductFilt(productCates.get(6)));
		
		return "xdm/product/ProductXdmList";
	}
	
	@RequestMapping(value = "/xdm/product/ProductXdmForm")
	public String productXdmForm(Model model) {
		model.addAttribute("productType", productService.selectProductType());
		
		List<ProductDto> productCates = productService.selectProductCate();
		model.addAttribute("feedSalaryAge", productService.selectProductFilt(productCates.get(0)));
		model.addAttribute("feedType", productService.selectProductFilt(productCates.get(1)));
		model.addAttribute("feedEtc", productService.selectProductFilt(productCates.get(2)));
		model.addAttribute("feedSize", productService.selectProductFilt(productCates.get(3)));
		model.addAttribute("feedFunction", productService.selectProductFilt(productCates.get(4)));
		model.addAttribute("feedBrand", productService.selectProductFilt(productCates.get(5)));
		model.addAttribute("feedIngredient", productService.selectProductFilt(productCates.get(6)));
		return "xdm/product/ProductXdmForm";
	}
}
