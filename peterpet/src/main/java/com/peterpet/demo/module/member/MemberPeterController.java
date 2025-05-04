package com.peterpet.demo.module.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.module.base.BaseController;
import com.peterpet.demo.module.base.Constants;
import com.peterpet.demo.module.code.CodeDto;
import com.peterpet.demo.module.delivery.DeliveryService;
import com.peterpet.demo.module.delivery.DeliveryVo;
import com.peterpet.demo.module.payment.PaymentService;
import com.peterpet.demo.module.payment.PaymentVo;
import com.peterpet.demo.module.pet.PetDto;
import com.peterpet.demo.module.pet.PetService;
import com.peterpet.demo.module.pet.PetVo;
import com.peterpet.demo.module.product.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/peter/member")
public class MemberPeterController extends BaseController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	PetService petService;
	@Autowired
	MailService mailService;
	@Autowired
	ProductService productService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	DeliveryService deliveryService;
	
	@RequestMapping(value = "/LoginPeterForm")
	public String loginPeterForm(MemberDto memberDto, Model model) {
		return "peter/member/LoginPeterForm";
	}
	
	@RequestMapping(value = "/SignupPeterForm")
	public String singUpPeterForm() {
		return "peter/member/SignupPeterForm";
	}
	
	@RequestMapping(value = "/UpdatePeterForm")
	public String UpdatePeterForm(MemberDto memberDto) {
		memberService.update(memberDto);
		return "redirect:/peter/member/MyAccountPeterForm";
	}
	
	@RequestMapping(value = "/InsertPeterForm")
	public String InsertPeterForm(MemberDto memberDto) throws Exception {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					mailService.sendMailWelcome(memberDto);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
		memberDto.setUserPassword(encodeBcrypt(memberDto.getUserPassword(), 10));
		memberService.insert(memberDto);
		return "redirect:/peter/index/IndexPeterView";
	}
	
	@RequestMapping(value = "/MyAccountPeterForm")
	public String MyAccountPeterForm(MemberDto memberDto, PetVo petVo, @ModelAttribute("vo") MemberVo memberVo,
			@ModelAttribute("vo2") PaymentVo paymentVo, DeliveryVo deliveryVo,
			Model model, HttpSession httpSession) {
		List<PetDto> pets = new ArrayList<>();
		
		memberDto.setUserSeq((String)httpSession.getAttribute("sessSeqPeter"));
		petVo.setUserSeq((String)httpSession.getAttribute("sessSeqPeter"));
		memberVo.setUserSeq((String)httpSession.getAttribute("sessSeqPeter"));
		paymentVo.setUserSeq((String)httpSession.getAttribute("sessSeqPeter"));
		deliveryVo.setUserSeq((String)httpSession.getAttribute("sessSeqPeter"));
		
		pets = petService.selectListPeterPets(petVo);
		petVo.calculateAge(pets);
		
		memberVo.setParamsPaging(memberService.reviewCount(memberVo));
		paymentVo.setParamsPaging(paymentService.selectCount(paymentVo));
		
		model.addAttribute("item", memberService.selectOne(memberDto));
		model.addAttribute("list", pets);
		model.addAttribute("reviews", memberService.reviewList(memberVo));
		model.addAttribute("code", CodeDto.cachedCodeArrayList);
		model.addAttribute("pays", paymentService.selectList(paymentVo));
		model.addAttribute("deliveries", deliveryService.selectList(deliveryVo));
		return "peter/member/MyAccountPeterForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/CheckIdPeterProc")
	public Map<String, Object> checkIdPeterProc(MemberDto dto) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (memberService.checkId(dto) == 0) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/CheckEmailPeterProc")
	public Map<String, Object> checkEmailPeterProc(MemberDto dto) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (memberService.checkEmail(dto) == 0) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/PasswordChangePeterProc")
	public Map<String, Object> passwordChangePeterProc(MemberDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		MemberDto rtMember = memberService.selectOne(dto);
		
		if (matchesBcrypt(dto.getUserPassword(), rtMember.getUserPassword(), 10)) {
			dto.setUserPassword(encodeBcrypt(dto.getNewPassword(), 10));
			memberService.updatePassword(dto);
			httpSession.setAttribute("sessSeqPeter", null);
			httpSession.setAttribute("sessIdPeter", null);
			httpSession.setAttribute("sessNamePeter", null);
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/CheckPhonePeterProc")
	public Map<String, Object> checkPhonePeterProc(MemberDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		dto = memberService.checkPhone(dto);
		if (dto != null) {
			if (dto.getUserDelFlag() == 0) {
				returnMap.put("rt", "already");
			} else if (dto.getUserDelFlag() == 1) {
				if (dto.getDateDiff() <= 30) {
					returnMap.put("rt", "periord");
				} else {
					returnMap.put("rt", "success");
				}
			} else {
				returnMap.put("rt", "success");
			}
		} else {
			returnMap.put("rt", "success");
		}
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/LoginPeterProc")
	public Map<String, Object> loginPeterProc(MemberDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		MemberDto rtMember = memberService.selectOneLogin(dto);

		if (rtMember != null && matchesBcrypt(dto.getUserPassword(), rtMember.getUserPassword(), 10)) {
			httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
			httpSession.setAttribute("sessSeqPeter", rtMember.getUserSeq());
			httpSession.setAttribute("sessIdPeter", rtMember.getUserId());
			httpSession.setAttribute("sessNamePeter", rtMember.getUserName());
			
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fali");
		}
		
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/LogoutPeterProc")
	public Map<String, Object> logoutPeterProc(MemberDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		httpSession.setAttribute("sessSeqPeter", null);
		httpSession.setAttribute("sessIdPeter", null);
		httpSession.setAttribute("sessNamePeter", null);
		returnMap.put("rt", "success");
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/UserPeterDeltProc")
	public void userPeterDeltProc(MemberDto dto, HttpSession httpSession) throws Exception {
		dto.setUserSeq(httpSession.getAttribute("sessSeqPeter").toString());
		memberService.uelete(dto);
		httpSession.setAttribute("sessSeqPeter", null);
		httpSession.setAttribute("sessIdPeter", null);
		httpSession.setAttribute("sessNamePeter", null);
	}
	
	@ResponseBody
	@RequestMapping(value = "/ReviewPeterInst")
	public Map<String, Object> reviewPeterInst(MemberDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> rtMap = new HashMap<>();
		
		if (httpSession.getAttribute("sessSeqPeter") != null) {
			dto.setUserSeq((String)httpSession.getAttribute("sessSeqPeter"));
			memberService.reviewInsert(dto);
			productService.updateScore(dto);
			rtMap.put("rt", "success");
		} else {
			rtMap.put("rt", "fail");
		}
		return rtMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ReviewPeterProc")
	public Map<String, Object> reviewPeterProc(MemberVo memberVo) {
		Map<String, Object> rtMap = new HashMap<>();
		memberVo.setParamsPaging(memberService.reviewCount(memberVo));
		rtMap.put("list", memberService.reviewList(memberVo));
		rtMap.put("vo", memberVo);
		return rtMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ReviewPeterViewProc")
	public Map<String, Object> reviewPeterViewProc(MemberVo memberVo) {
		Map<String, Object> rtMap = new HashMap<>();
		rtMap.put("item", memberService.selectOneReview(memberVo));
		return rtMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ReviewPeterUpdtProc")
	public void reviewPeterUpdtProc(MemberDto memberDto) throws Exception {
		memberService.updateReview(memberDto);
	}
	
	@ResponseBody
	@RequestMapping(value = "/ReviewPeterDeltProc")
	public void reviewPeterDeltProc(MemberDto memberDto) {
		memberService.deleteReview(memberDto);
	}
}
