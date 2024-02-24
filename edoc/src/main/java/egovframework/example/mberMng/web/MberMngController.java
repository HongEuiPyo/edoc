package egovframework.example.mberMng.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.example.mberMng.service.MberMngSearchVO;
import egovframework.example.mberMng.service.MberMngService;
import egovframework.example.mberMng.service.MberMngVO;

@RequestMapping(value = "/mberMng")
@Controller
public class MberMngController {

	@Resource(name = "propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name = "mberMngService")
	private MberMngService mberMngService;
	
	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(MberMngController.class);
	
	/**
	 * 회원 관리 목록
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mberMngList.do")
    public String mberMngList(@ModelAttribute("mberMngSearchVO") MberMngSearchVO mberMngSearchVO, Model model) throws Exception {
		LOGGER.info("/mberMngList.do 호출");
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "redirect:/login/login.do";
		}
		
		mberMngSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		mberMngSearchVO.setPageSize(propertiesService.getInt("pageSize"));
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mberMngSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mberMngSearchVO.getPageIndex());
		paginationInfo.setPageSize(mberMngSearchVO.getPageSize());
		
		mberMngSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mberMngSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mberMngSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<?> mberMngVOList = mberMngService.mberMngList(mberMngSearchVO);
		model.addAttribute("resultList", mberMngVOList);

		int totCnt = mberMngService.mberMngVOListTotCnt(mberMngSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "mberMng/mberMngList";
    } 
	
	/**
	 * 회원 관리 상세
	 * 
	 * @param mberMngVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mberMngDetail.do")
	public String mberMngDetail(@ModelAttribute("mberMngVo") MberMngVO mberMngVo, Model model) throws Exception {
		LOGGER.info("/mberMngDetail.do 호출");
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "redirect:/login/login.do";
		}
		
		MberMngVO mberMngDetail = mberMngService.selectMberMngDetail();
		model.addAttribute("mberMngDetail", mberMngDetail);
		return "mberMng/mberMngDetail";
	}
	
	/**
	 * 회원 관리 폼
	 * 
	 * @param request
	 * @param mberMngVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/insertMberMng.do", "/updateMberMng.do"}, method = RequestMethod.POST)
	public String mberMngForm(HttpServletRequest request, MberMngVO mberMngVo, Model model) throws Exception {
		LOGGER.info("mberMngForm 메서드 호출");
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "redirect:/login/login.do";
		}
		
		String requestUri = request.getRequestURI(); 
		
		if (requestUri.indexOf("insert") > 1) {
			return "mberMng/mberMngForm";
		} else {
			MberMngVO result = mberMngService.selectMberMngDetail();
			model.addAttribute("result", result);
			return "mberMng/mberMngForm";			
		}
	}	
	
	/**
	 * 회원 관리 등록 처리
	 * 
	 * @param mberMngVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertMberMngAjax.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public HashMap<String, Object> insertMberMngAjax(@RequestParam("mberMngVo") MberMngVO mberMngVo, Model model) throws Exception {
		LOGGER.info("/insertMberMngAjax.do 호출");
		
		HashMap<String, Object> resultMap = new HashMap<>();
		
		MberMngVO result = mberMngService.insertMberMng(mberMngVo);
		
		if (result != null) {
			resultMap.put("success", "ok");
			resultMap.put("result", result);

		} else {
			resultMap.put("success", "fail");
		}
		
		return resultMap; // json 객체 반환
	}
	
	/**
	 * 회원 관리 수정 처리
	 * 
	 * @param mberMngVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMberMngAjax.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public HashMap<String, Object> updateMberMngAjax(@RequestParam("mberMngVo") MberMngVO mberMngVo) throws Exception {
		LOGGER.info("/updateMberMngAjax.do 호출");

		HashMap<String, Object> resultMap = new HashMap<>();
		
		MberMngVO result = mberMngService.updateMberMng(mberMngVo);
		
		if (result != null) {
			resultMap.put("success", "ok");
			resultMap.put("result", result);

		} else {
			resultMap.put("success", "fail");
		}
		
		return resultMap; // json 객체 반환 라이브러리 또는 json 객체 변환 필요
	}
	
	/**
	 * 회원 관리 삭제
	 * 
	 * @param mberMngVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteMberMng.do", method = RequestMethod.POST)
	public String deleteMberMng(MberMngVO mberMngVo) throws Exception {
		LOGGER.info("/deleteMberMng.do 호출");
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "redirect:/login/login.do";
		}
		
		mberMngService.deleteMberMng(mberMngVo);
		return "redirect:/mberMng/mberMngList.do";
	}
	
}
