package egovframework.example.login.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.example.login.service.LoginService;
import egovframework.example.login.service.LoginVO;

@Controller
public class LoginController {

	@Resource(name = "loginService")
	private LoginService loginService;
	
	/**
	 * 로그인한다.
	 * @return
	 */
	@RequestMapping(value = "/login.do")
	public String getLogin() {
		return "login/login";
	}

	/**
	 * 세션 로그인을 처리한다.
	 * @param req
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loginProc.do")
	public String loginProc(HttpServletRequest req, LoginVO vo) throws Exception {		
		LoginVO loginVO = loginService.authenticate(vo);
		
		if (loginVO != null) {
			req.getSession().setAttribute("loginUser", loginVO);
			return "redirect:/loginSuccess.do";
		} else {
			return "login/login";
		}
		
	}
	
	/**
	 * 로그인 후 메인화면으로 들어간다.
	 * @return
	 */
	@RequestMapping(value = "/loginSuccess.do", method = RequestMethod.GET)
	public String loginSuccess() {
		return "redirect:/";
	}
	
	/**
	 * 로그아웃한다.
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		req.getSession().setAttribute("loginUser", null);
		return "redirect:/";
	}
	
}
