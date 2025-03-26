package com.peterpet.demo.module.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/xdm/index")
public class IndexController {

	@RequestMapping(value = "/IndexXdmView")
	public String indexXdmView() {
		return "xdm/index/IndexXdmView";
	}
}
