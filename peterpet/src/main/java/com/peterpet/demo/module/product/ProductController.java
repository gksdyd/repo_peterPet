package com.peterpet.demo.module.product;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.common.common.Constants;
import com.peterpet.demo.module.base.BaseController;
import com.peterpet.demo.module.code.CodeDto;
import com.peterpet.demo.module.code.CodeService;

import jakarta.servlet.http.HttpServletResponse;

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
		
		if (!productDto.getProdFuncArray().isEmpty()) {
			for (int i = 0; i < productDto.getProdFuncArray().size(); i++) {
				if (i == 0) {
					productDto.setFuncMain(1);
				} else {
					productDto.setFuncMain(0);
				}
				productDto.setFuncName(productDto.getProdFuncArray().get(i));
				productService.funcInsert(productDto);
			}
		}
		
		for (int i = 0; i < productDto.getFeedPriceArray().size(); i++) {
			if (i == 0) {
				productDto.setInfoMain(1);
			} else {
				productDto.setInfoMain(0);
			}
			productDto.setInfoPrice(productDto.getFeedPriceArray().get(i));
			if (productDto.getProdType() != 3) {
				productDto.setInfoWeight(productDto.getFeedWeightArray().get(i));				
			}
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
		if (productDto.getProdType() != 3) {
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
		} else {
			size = 0;
			flag = 0;
		}
		
		// 사료의 기능 갯수 증가, 감소에 따른 insert, update, delete
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				productDto.setFuncMain(1);
			} else {
				productDto.setFuncMain(0);
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
		if (productDto.getProdType() != 3) {
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
		} else {
			size = 0;
			flag = 0;
			if (productDto.getFeedInfoSeqArray().isEmpty()) {
				productDto.setInfoMain(1);
				productDto.setInfoPrice(productDto.getFeedPriceArray().get(0));
				productDto.setInfoDiscount(productDto.getFeedDiscountArray().get(0));
				productService.infoInsert(productDto);
			} else {
				productDto.setInfoSeq(productDto.getFeedInfoSeqArray().get(0));
				productDto.setInfoPrice(productDto.getFeedPriceArray().get(0));
				productDto.setInfoDiscount(productDto.getFeedDiscountArray().get(0));
				productService.infoUpdate(productDto);
			}
		}
		
		// 사료의 정보 갯수 증가, 감소에 따른 insert, update, delete
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				productDto.setInfoMain(1);
			} else {
				productDto.setInfoMain(0);
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
	
	@ResponseBody
	@RequestMapping(value = "/ProductXdmImageDeleProc")
	public void productXdmImageDeleProc(ProductDto dto) {
		productService.updateUploaded(dto);
	}
	
	@RequestMapping(value = "/ProductXdmFilterProc")
	public String productXdmFilterProc(@ModelAttribute("vo") ProductVo vo) {
		return "xdm/include/filter :: filter";
	}
	
	@RequestMapping(value = "/ProductXdmFormProc")
	public String productXdmFormProc(@ModelAttribute("vo") ProductVo vo) {
		return "xdm/include/form :: form";
	}
	
	@RequestMapping(value = "/ProductXdmSupplyProc")
	public String productXdmSupplyProc(@ModelAttribute("vo") ProductVo vo) {
		return "xdm/include/supplies";
	}
	
	@RequestMapping(value = "/ProductXdmSupplyFormProc")
	public String productXdmSupplyFormProc(@ModelAttribute("item") ProductDto dto) {
		return "xdm/include/suppliesForm";
	}
	
	@RequestMapping("/excelDownload")
    public void excelDownload(ProductVo vo, HttpServletResponse httpServletResponse) throws Exception {
		
		initSearchTime(vo);
		vo.setParamsPaging(productService.selectOneCount(vo));

		if (vo.getTotalRows() > 0) {
			List<ProductDto> list = productService.selectList(vo);
			
//			Workbook workbook = new HSSFWorkbook();	// for xls
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet(vo.getName());
	        CellStyle cellStyle = workbook.createCellStyle();        
	        Row row = null;
	        Cell cell = null;
	        int rowNum = 0;
			
//	        each column width setting
	        sheet.setColumnWidth(0, 2100);
	        sheet.setColumnWidth(1, 3100);

	        row = sheet.createRow(rowNum++);
			for(int i=0; i<vo.getHeader().size(); i++) {
				cell = row.createCell(i);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
				cell.setCellValue(vo.getHeader().get(i));
			}

//	        Body
			DecimalFormat format = new DecimalFormat("###,###");
			int num = 1;
	        for (int i=0; i<list.size(); i++) {
	            row = sheet.createRow(rowNum++);
	            
//	            String type: null 전달 되어도 ok
//	            int, date type: null 시 오류 발생 하므로 null check
//	            String type 이지만 정수형 데이터가 전체인 seq 의 경우 캐스팅
	            if (vo.getProdType() == 1) {
	            	cell = row.createCell(0);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(num);
	            	
	            	cell = row.createCell(1);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(list.get(i).getProdUseFlag() == 1 ? "Y" : "N");
	            	
	            	cell = row.createCell(2);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(list.get(i).getProdName());
	            	
	            	cell = row.createCell(3);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	if (list.get(i).getFeedSalaryAge() != null) {
	            		cell.setCellValue(CodeDto.cachedCodeArrayList.get(list.get(i).getFeedSalaryAge() - 1).getCodeName());	            		
	            	}
	            	
	            	cell = row.createCell(4);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	if (list.get(i).getFeedType() != null) {
	            		cell.setCellValue(CodeDto.cachedCodeArrayList.get(list.get(i).getFeedType() - 1).getCodeName());	            		
	            	}
	            	
	            	cell = row.createCell(5);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	if (list.get(i).getFeedEtc() != null) {
	            		cell.setCellValue(CodeDto.cachedCodeArrayList.get(list.get(i).getFeedEtc() - 1).getCodeName());	            		
	            	}
	            	
	            	cell = row.createCell(6);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	if (list.get(i).getFeedSize() != null) {
	            		cell.setCellValue(CodeDto.cachedCodeArrayList.get(list.get(i).getFeedSize() - 1).getCodeName());	            		
	            	}
	            	
	            	cell = row.createCell(7);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(list.get(i).getFuncCount());
	            	
	            	cell = row.createCell(8);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	if (list.get(i).getFeedBrand() != null) {
	            		cell.setCellValue(CodeDto.cachedCodeArrayList.get(list.get(i).getFeedBrand() - 1).getCodeName());	            		
	            	}
	            	
	            	cell = row.createCell(9);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	if (list.get(i).getFeedIngredient() != null) {
	            		cell.setCellValue(CodeDto.cachedCodeArrayList.get(list.get(i).getFeedIngredient() - 1).getCodeName());	            		
	            	}
	            	
	            	cell = row.createCell(10);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(list.get(i).getInfoCount());
	            	
	            	cell = row.createCell(11);
		        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
		        	cell.setCellStyle(cellStyle);
		        	if(list.get(i).getProdRegDate() != null) cell.setCellValue(list.get(i).getProdRegDate());
		        	
		        	cell = row.createCell(12);
		        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
		        	cell.setCellStyle(cellStyle);
		        	if(list.get(i).getProdModDate() != null) cell.setCellValue(list.get(i).getProdModDate());
	            } else if (vo.getProdType() == 2) {
	            	cell = row.createCell(0);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(num);
	            	
	            	cell = row.createCell(1);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(list.get(i).getProdUseFlag() == 1 ? "Y" : "N");
	            	
	            	cell = row.createCell(2);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(list.get(i).getProdName());
	            	
	            	cell = row.createCell(3);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	if (list.get(i).getFeedType() != null) {
	            		cell.setCellValue(CodeDto.cachedCodeArrayList.get(list.get(i).getFeedType() - 1).getCodeName());	            		
	            	}
	            	
	            	cell = row.createCell(4);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	if (list.get(i).getSnckFeature() != null) {
	            		cell.setCellValue(CodeDto.cachedCodeArrayList.get(list.get(i).getSnckFeature() - 1).getCodeName());	            		
	            	}
	            	
	            	cell = row.createCell(5);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(list.get(i).getFuncCount());
	            	
	            	cell = row.createCell(6);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	if (list.get(i).getFeedBrand() != null) {
	            		cell.setCellValue(CodeDto.cachedCodeArrayList.get(list.get(i).getFeedBrand() - 1).getCodeName());	            		
	            	}
	            	
	            	cell = row.createCell(7);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	if (list.get(i).getFeedIngredient() != null) {
	            		cell.setCellValue(CodeDto.cachedCodeArrayList.get(list.get(i).getFeedIngredient() - 1).getCodeName());	            		
	            	}
	            	
	            	cell = row.createCell(8);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(list.get(i).getInfoCount());
	            	
	            	cell = row.createCell(9);
		        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
		        	cell.setCellStyle(cellStyle);
		        	if(list.get(i).getProdRegDate() != null) cell.setCellValue(list.get(i).getProdRegDate());
		        	
		        	cell = row.createCell(10);
		        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
		        	cell.setCellStyle(cellStyle);
		        	if(list.get(i).getProdModDate() != null) cell.setCellValue(list.get(i).getProdModDate());
	            } else if (vo.getProdType() == 3) {
	            	cell = row.createCell(0);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(num);
	            	
	            	cell = row.createCell(1);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(list.get(i).getProdUseFlag() == 1 ? "Y" : "N");
	            	
	            	cell = row.createCell(2);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(list.get(i).getProdName());
	            	
	            	cell = row.createCell(3);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	if (list.get(i).getFeedType() != null) {
	            		cell.setCellValue(CodeDto.cachedCodeArrayList.get(list.get(i).getFeedType() - 1).getCodeName());	            		
	            	}
	            	
	            	cell = row.createCell(4);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	if (list.get(i).getFeedBrand() != null) {
	            		cell.setCellValue(CodeDto.cachedCodeArrayList.get(list.get(i).getFeedBrand() - 1).getCodeName());	            		
	            	}
	            	
	            	cell = row.createCell(5);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(format.format(list.get(i).getInfoPrice()));
	            	
	            	cell = row.createCell(6);
	            	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            	cell.setCellStyle(cellStyle);
	            	cell.setCellValue(list.get(i).getInfoDiscount() + "%");
	            	
	            	cell = row.createCell(7);
		        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
		        	cell.setCellStyle(cellStyle);
		        	if(list.get(i).getProdRegDate() != null) cell.setCellValue(list.get(i).getProdRegDate());
		        	
		        	cell = row.createCell(8);
		        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
		        	cell.setCellStyle(cellStyle);
		        	if(list.get(i).getProdModDate() != null) cell.setCellValue(list.get(i).getProdModDate());
	            }
	            num++;
	        }

	        LocalDate now = LocalDate.now();
	        
	        httpServletResponse.setContentType("ms-vnd/excel");
//	        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=example.xls");	// for xls
	        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + vo.getName() + now + ".xlsx");

	        workbook.write(httpServletResponse.getOutputStream());
	        workbook.close();
		}
    }
}
