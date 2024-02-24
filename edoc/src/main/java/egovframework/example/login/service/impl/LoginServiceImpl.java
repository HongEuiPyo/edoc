package egovframework.example.login.service.impl;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.login.service.LoginService;
import egovframework.example.login.service.LoginVO;

@Service("loginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {
	
	@Resource(name = "loginDAO")
	private LoginDAO loginDAO;
	
	/**
	 * 로그인
	 */
	@Override
	public LoginVO authenticate(LoginVO vo) throws Exception {
		return loginDAO.authenticate(vo);
	}
	
	
	
}
