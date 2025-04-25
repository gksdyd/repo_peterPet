package com.peterpet.demo.module.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.common.common.Constants;
import com.peterpet.demo.module.base.BaseController;
import com.peterpet.demo.module.code.CodeService;

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
	public String productXdmForm(Model model, ProductDto productDto, @ModelAttribute("vo") ProductVo vo) throws Exception {
		vo.InitProdType();
		if (vo.getRegisterOrModifyFlag() == 1) {
			if (productService.selectMaxSeq() == null) {
				productDto.setProdSeq("1");
			} else {
				productDto.setProdSeq((productService.selectMaxSeq() + 1) + "");
			}
			model.addAttribute("item", productDto);
		} else {
			model.addAttribute("item", productService.selectOnePoduct(vo));
			
			List<ProductDto> dtoFunction = productService.selectOneFunctions(vo);
			for (int i = 0; i < dtoFunction.size(); i++) {
				vo.getProdFuncSeqArray().add(dtoFunction.get(i).getFuncSeq());
				vo.getProdFuncArray().add(dtoFunction.get(i).getFuncName());
				vo.getProdFuncNameArray().add((CodeService.selectOneCachedCode(Integer.parseInt(dtoFunction.get(i).getFuncName()))));
			}
			
			List<ProductDto> dtoInfo = productService.selectOneInfos(vo);
			for (int i = 0; i < dtoInfo.size(); i++) {
				vo.getFeedInfoSeqArray().add(dtoInfo.get(i).getInfoSeq());
				vo.getFeedPriceArray().add(dtoInfo.get(i).getInfoPrice());
				vo.getFeedWeightArray().add(dtoInfo.get(i).getInfoWeight());
				vo.getFeedDiscountArray().add(dtoInfo.get(i).getInfoDiscount());
			}
			model.addAttribute("image1", productService.selectImage1(vo));
			model.addAttribute("image2", productService.selectImage2(vo));
		}
		return "xdm/product/ProductXdmForm";
	}

	@RequestMapping(value = "/ProductXdmInst")
	public String codeGroupXdmInst(ProductDto productDto) {
		productService.insert(productDto);
		
		for (int i = 0; i < productDto.getProdFuncArray().size(); i++) {
			if (i == 0) {
				productDto.setFuncMain(1);
			}
			productDto.setFuncName(productDto.getProdFuncArray().get(i));
			productService.funcInsert(productDto);
		}
		
		for (int i = 0; i < productDto.getFeedPriceArray().size(); i++) {
			if (i == 0) {
				productDto.setInfoMain(1);
			}
			productDto.setInfoPrice(productDto.getFeedPriceArray().get(i));
			productDto.setInfoWeight(productDto.getFeedWeightArray().get(i));
			productDto.setInfoDiscount(productDto.getFeedDiscountArray().get(i));
			productService.infoInsert(productDto);
		}
		return "redirect:/xdm/product/ProductXdmList";
	}
	
	@RequestMapping(value = "/ProductXdmUpdt")
	public String productXdmUpdt(ProductDto productDto) {
		int size;
		int flag;
		
		productService.update(productDto);
		
		// 사료의 기능 갯수 증가, 감소 여부 체크
		if (productDto.getProdFuncSeqArray().size() > productDto.getProdFuncArray().size()) {
			size = productDto.getProdFuncSeqArray().size();
			flag = Constants.PRODUCT_UPDATE_COUNT_DECREASE;
		} else if (productDto.getProdFuncSeqArray().size() < productDto.getProdFuncArray().size()) {
			size = productDto.getProdFuncArray().size();
			flag = Constants.PRODUCT_UPDATE_COUNT_INCREASE;
		} else {
			size = productDto.getProdFuncSeqArray().size();
			flag = Constants.PRODUCT_UPDATE_COUNT_MAINTAIN;
		}
		
		// 사료의 기능 갯수 증가, 감소에 따른 insert, update, delete
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				productDto.setFuncMain(1);
			}
			if (flag == Constants.PRODUCT_UPDATE_COUNT_DECREASE) {
				if (i < productDto.getProdFuncArray().size()) {
					productDto.setFuncSeq(productDto.getProdFuncSeqArray().get(i));
					productDto.setFuncName(productDto.getProdFuncArray().get(i));
					productService.funcUpdate(productDto);				
				} else {
					productDto.setFuncSeq(productDto.getProdFuncSeqArray().get(i));
					productService.funcDelete(productDto);	
				}
			} else if (flag == Constants.PRODUCT_UPDATE_COUNT_INCREASE) {
				if (i < productDto.getProdFuncSeqArray().size()) {
					productDto.setFuncSeq(productDto.getProdFuncSeqArray().get(i));
					productDto.setFuncName(productDto.getProdFuncArray().get(i));
					productService.funcUpdate(productDto);				
				} else {
					productDto.setFuncName(productDto.getProdFuncArray().get(i));
					productService.funcInsert(productDto);	
				}
			} else if (flag == Constants.PRODUCT_UPDATE_COUNT_MAINTAIN) {
				productDto.setFuncSeq(productDto.getProdFuncSeqArray().get(i));
				productDto.setFuncName(productDto.getProdFuncArray().get(i));
				productService.funcUpdate(productDto);	
			}
		}
		
		// 사료의 정보 갯수 증가, 감소 여부 체크
		if (productDto.getFeedInfoSeqArray().size() > productDto.getFeedPriceArray().size()) {
			size = productDto.getFeedInfoSeqArray().size();
			flag = Constants.PRODUCT_UPDATE_COUNT_DECREASE;
		} else if (productDto.getFeedInfoSeqArray().size() < productDto.getFeedPriceArray().size()) {
			size = productDto.getFeedPriceArray().size();
			flag = Constants.PRODUCT_UPDATE_COUNT_INCREASE;
		} else {
			size = productDto.getFeedInfoSeqArray().size();
			flag = Constants.PRODUCT_UPDATE_COUNT_MAINTAIN;
		}
		
		// 사료의 정보 갯수 증가, 감소에 따른 insert, update, delete
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				productDto.setInfoMain(1);
			}
			if (flag == Constants.PRODUCT_UPDATE_COUNT_DECREASE) {
				if (i < productDto.getFeedPriceArray().size()) {
					productDto.setInfoSeq(productDto.getFeedInfoSeqArray().get(i));
					productDto.setInfoPrice(productDto.getFeedPriceArray().get(i));
					productDto.setInfoWeight(productDto.getFeedWeightArray().get(i));
					productDto.setInfoDiscount(productDto.getFeedDiscountArray().get(i));
					productService.infoUpdate(productDto);				
				} else {
					productDto.setInfoSeq(productDto.getFeedInfoSeqArray().get(i));
					productService.infoDelete(productDto);	
				}
			} else if (flag == Constants.PRODUCT_UPDATE_COUNT_INCREASE) {
				if (i < productDto.getFeedInfoSeqArray().size()) {
					productDto.setInfoSeq(productDto.getFeedInfoSeqArray().get(i));
					productDto.setInfoPrice(productDto.getFeedPriceArray().get(i));
					productDto.setInfoWeight(productDto.getFeedWeightArray().get(i));
					productDto.setInfoDiscount(productDto.getFeedDiscountArray().get(i));
					productService.infoUpdate(productDto);			
				} else {
					productDto.setInfoPrice(productDto.getFeedPriceArray().get(i));
					productDto.setInfoWeight(productDto.getFeedWeightArray().get(i));
					productDto.setInfoDiscount(productDto.getFeedDiscountArray().get(i));
					productService.infoInsert(productDto);
				}
			} else if (flag == Constants.PRODUCT_UPDATE_COUNT_MAINTAIN) {
				productDto.setInfoSeq(productDto.getFeedInfoSeqArray().get(i));
				productDto.setInfoPrice(productDto.getFeedPriceArray().get(i));
				productDto.setInfoWeight(productDto.getFeedWeightArray().get(i));
				productDto.setInfoDiscount(productDto.getFeedDiscountArray().get(i));
				productService.infoUpdate(productDto);
			}
		}
		return "redirect:/xdm/product/ProductXdmList";
	}
	
	@RequestMapping(value = "/ProductXdmDele")
	public String productXdmDele(ProductDto productDto) {
		// 사료의 기능 삭제
		for (int i = 0; i < productDto.getProdFuncSeqArray().size(); i++) {
			productDto.setFuncSeq(productDto.getProdFuncSeqArray().get(i));
			productService.funcDelete(productDto);
		}
		
		// 사료의 정보 삭제
		for (int i = 0; i < productDto.getFeedInfoSeqArray().size(); i++) {
			productDto.setInfoSeq(productDto.getFeedInfoSeqArray().get(i));
			productService.infoDelete(productDto);
		}
		
		productService.delete(productDto);
		
		return "redirect:/xdm/product/ProductXdmList";
	}
	
	@RequestMapping(value = "/ProductXdmUele")
	public String productXdmUele(ProductDto productDto) {	
		productService.uelete(productDto);
		
		return "redirect:/xdm/product/ProductXdmList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/ProductXdmDeltProc")
	public void productXdmDeltProc(ProductVo vo) {
		productService.severalDelete(vo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/ProductXdmUeltProc")
	public void productXdmUeltProc(ProductVo vo) {
		productService.severalUelete(vo);
	}
	
	@RequestMapping(value = "/ProductXdmImageInst")
	public String productXdmImageInst(ProductDto productDto) throws Exception {
		productService.imageInsert(productDto);
		return "redirect:/xdm/product/ProductXdmList";
	}
}
