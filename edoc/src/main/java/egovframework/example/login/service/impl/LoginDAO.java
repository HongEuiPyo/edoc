package egovframework.example.login.service.impl;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.example.login.service.LoginVO;

@Repository("loginDAO")
public class LoginDAO extends EgovAbstractMapper {
	
	/**
	 * 로그인
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public LoginVO authenticate(LoginVO vo) throws Exception {
		return (LoginVO) selectOne("egovframework.example.login.service.impl.LoginDAO.authenticate", vo);
	}
	
	
	
}
