package egovframework.example.join.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.example.join.service.JoinService;
import egovframework.example.join.service.JoinVO;

@Controller
public class JoinController {

	@Resource(name = "joinService")
	private JoinService joinService; 
	
	/**
	 * 회원가입
	 * @return
	 */
	@RequestMapping(value = "/join.do")
	public String getJoin() {
		return "join/join";
	}

	/**
	 * 회원가입 처리
	 * @param joinVO
	 * @return
	 */
	@RequestMapping(value = "/joinProc.do", method=RequestMethod.POST)
	public String joinProc(JoinVO vo) throws Exception {
		String result = joinService.join(vo);
		
		if (Integer.parseInt(result) > 0) {
			return "redirect:/";
		} else {
			return "join/join";
		}
		
	}
	
	
	
}
