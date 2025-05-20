package com.peterpet.demo.module.index;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peterpet.demo.module.product.ProductDto;
import com.peterpet.demo.module.product.ProductService;

@Controller
@RequestMapping(value = "/peter/index")
public class IndexPeterController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/IndexPeterView")
	public String indexPeterController(Model model) {
		List<ProductDto> selectDtos = new ArrayList<>();
		List<ProductDto> dtos = productService.selectAllProducts();
		int[] index = new int[8];
		
		for (int i = 0; i < index.length; i++) {
			int temp = (int)((Math.random()) * dtos.size());
			if (i == 0) {
				index[i] = temp;
				selectDtos.add(dtos.get(temp));
				selectDtos.get(i).calculatePrice();
			}
			for (int j = 0; j < i; j++) {
				if (temp == index[j]) {
					i--;
					break;
				} else if (j == i - 1) {
					index[i] = temp;
					selectDtos.add(dtos.get(temp));
					selectDtos.get(i).calculatePrice();
				}
			}
		}
		
		model.addAttribute("list", selectDtos);
		return "peter/index/IndexPeterView";
	}
}
