package com.peterpet.demo.module.code;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.peterpet.demo.module.base.BaseController;
import com.peterpet.demo.module.member.MemberVo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/xdm/code")
public class CodeController extends BaseController {

	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/CodeXdmList")
	public String codeXdmList(@ModelAttribute("vo") CodeVo vo, Model model) {
		initSearchTime(vo);
		vo.setParamsPaging(codeService.selectOneCount(vo));
		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", codeService.selectList(vo));
		}		
		return "xdm/code/CodeXdmList";
	}
	
	@RequestMapping(value = "/CodeXdmForm")
	public String codeXdmForm(Model model, CodeDto codeDto, @ModelAttribute("vo") CodeVo vo) {
		if (vo.getRegisterOrModifyFlag() == 1) {
			if (codeService.selectMaxSeq() == null)
			{
				codeDto.setCodeSeq("1");
			} else {
				codeDto.setCodeSeq((codeService.selectMaxSeq() + 1) + "");
			}
			model.addAttribute("item", codeDto);
			model.addAttribute("list", codeService.selectCodeGroupName());
		} else {
			model.addAttribute("item", codeService.selectOne(codeDto));
			model.addAttribute("list", codeService.selectCodeGroupName());
		}
		return "xdm/code/CodeXdmForm";
	}
	
	@RequestMapping(value = "/CodeXdmInst")
	public String codeXdmInst(CodeDto codeDto) {
		codeService.insert(codeDto);
		return "redirect:/xdm/code/CodeXdmList";
	}
	
	@RequestMapping(value = "/CodeXdmUpdt")
	public String codeXdmUpdt(CodeDto codeDto) {
		codeService.update(codeDto);
		return "redirect:/xdm/code/CodeXdmList";
	}
	
	@RequestMapping(value = "/CodeXdmUelt")
	public String codeGroupXdmUelt(CodeDto codeDto) {
		codeService.uelete(codeDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
	
	@RequestMapping(value = "/CodeXdmDelt")
	public String codeGroupXdmDelt(CodeDto codeDto) {
		codeService.delete(codeDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/CodeXdmDeltProc")
	public void codeXdmDeltProc(CodeVo vo) {
		codeService.severalDelete(vo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/CodeXdmUeltProc")
	public void codeXdmUeltProc(CodeVo vo) {
		codeService.severalUelete(vo);
	}
	
	@RequestMapping(value = "/CodeXdmExcelProc")
	public void codeXdmExcelProc(HttpServletResponse response, CodeVo vo) throws IOException {
		initSearchTime(vo);
		vo.setParamsPaging(codeService.selectOneCount(vo));
		List<CodeDto> dtos = null;
		if (vo.getTotalRows() > 0) {
			dtos = codeService.selectList(vo);
		}

		LocalDate now = LocalDate.now();
	    response.setContentType("text/xls; charset=UTF-8");
	    response.setHeader("Content-Disposition", "attachment; filename = " + vo.getName() + now + ".xls");
	    
	    OutputStream out = response.getOutputStream();
	    out.write(new byte[] { (byte)0xEF, (byte)0xBB, (byte)0xBF }); // BOM
	    OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
	    BufferedWriter writer = new BufferedWriter(osw);
	   
	    for (int i = 0; i < vo.getHeader().size(); i++) {
	    	writer.write(vo.getHeader().get(i));
	    	if (i != vo.getHeader().size() - 1) {
	            writer.write(",");
	        }
	    }
	    writer.newLine();
	    
	    int num = 1;
	    for (CodeDto dto : dtos) {
	        writer.write(String.join(",",
	        	String.valueOf(num),
        		dto.getCodeUseFlag() == 1 ? "Y" : "N",
				defaultString(dto.getCodeGroup_cogrSeq()),
	            defaultString(dto.getCogrName()),
	            defaultString(dto.getCodeSeq()),
	            defaultString(dto.getCodeNum()),
	            defaultString(dto.getCodeName()),
	            defaultString(dto.getCodeNameEng()),
	            defaultString(dto.getCodeOrder()),
				defaultString(dto.getCodeRegDate()),
	            defaultString(dto.getCodeModDate())
	        ));
	        writer.newLine();
	        num++;
	    }
	    writer.flush();
	    writer.close();
	}
	
	private String defaultString(Object obj) {
	    return obj == null ? "" : obj.toString();
	}
	
	@RequestMapping(value = "/UploadXdmForm")
	public String uploadXdmForm(@ModelAttribute("vo") MemberVo vo) {
		return "xdm/code/CodeXdmUpload";
	}
	
	@RequestMapping(value = "/ReadXdmExcel")
	public String readXdmExcel(@RequestParam("file") MultipartFile file, Model model) throws Exception { 		
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		
		List<CodeDto> dtos = new ArrayList<>();
		
		int errFlag = 0;
		for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) { 
			int flag = 0;
		    DataFormatter formatter = new DataFormatter();		        
		    XSSFRow row = worksheet.getRow(i);
		    	    	
		    String name = formatter.formatCellValue(row.getCell(0));
		    String engName = formatter.formatCellValue(row.getCell(1));
		    String num = formatter.formatCellValue(row.getCell(2));
		    String useFlag = formatter.formatCellValue(row.getCell(3));
		    String order = formatter.formatCellValue(row.getCell(4));
		    String discription = formatter.formatCellValue(row.getCell(5));
		    String pseq = formatter.formatCellValue(row.getCell(6));
			
	        if (name == null || name.equals("")) {
	        	errFlag = 1;
	        	flag = 1;
	        }
	        
	        if (useFlag == null || useFlag.equals("")) {
	        	errFlag = 1;
	        	flag = 1;
	        }
			
			try {
				if (pseq == null || pseq.equals("") || Integer.parseInt(pseq) <= 0) {
					errFlag = 1;
					flag = 1;
				}				
			} catch(NumberFormatException e) {
				errFlag = 1;
				flag = 1;
			}
			
			try {
				if (Integer.parseInt(order) <= 0) {
					errFlag = 1;
					flag = 1;
				}
			} catch (NumberFormatException e) {
				errFlag = 1;
				flag = 1;
			}
			
			CodeDto excel = new CodeDto();
			if (flag == 0) {
				if (useFlag.toUpperCase().equals("N")) {
					useFlag = "0";
				} else if (useFlag.toUpperCase().equals("Y")) {
					useFlag = "1";
				}
				
				excel.setCodeName(name);
				excel.setCodeNameEng(engName);
				excel.setCodeNum(num);
				excel.setCodeUseFlag(Integer.parseInt(useFlag));
				excel.setCodeOrder(Integer.parseInt(order));
				excel.setCodeDisc(discription);
				excel.setCodeGroup_cogrSeq(pseq);
				
				codeService.insert(excel);
			} else {
				excel.setCodeName(name);
				excel.setCodeNameEng(engName);
				excel.setCodeNum(num);
				excel.setErrUseFlag(useFlag);
				excel.setErrOrder(order);
				excel.setCodeDisc(discription);
				excel.setCodeGroup_cogrSeq(pseq);
				
				dtos.add(excel);
			}
		}
		
		model.addAttribute("list", dtos);
		return "xdm/code/UploadErrFile";			
	}
}
