package com.peterpet.demo.module.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/peter/index")
public class IndexPeterController {

	@RequestMapping(value = "/IndexPeterView")
	public String indexPeterController() {
		return "peter/index/IndexPeterView";
	}
}
