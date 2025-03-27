package com.peterpet.demo.peter.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPeterController {

	@RequestMapping(value = "/peter/index/IndexPeterView")
	public String indexPeterController() {
		return "peter/index/IndexPeterView";
	}
}
