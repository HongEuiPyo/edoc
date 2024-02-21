package egovframework.example.sample.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login.do")
	public String getLogin() {
		return "login/login";
	}
	
	@RequestMapping(value = "/loginProc.do")
	public String loginProc(HttpServletRequest req, LoginVO vo) {
		LoginVO loginVO = loginService.authenticate(vo);
		
		if (loginVO != null) {
			req.getSession().setAttribute("userAccount", loginVO);
			return "redirect:/loginSuccess.do";
		} else {
			return "login/login";
		}
		
	}
	
	
	
}
