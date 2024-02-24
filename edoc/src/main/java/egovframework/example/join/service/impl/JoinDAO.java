package egovframework.example.join.service.impl;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.example.join.service.JoinVO;

@Repository("joinDAO")
public class JoinDAO extends EgovAbstractMapper {

	/**
	 * 회원가입
	 * @param joinVO
	 * @return
	 */
	public String insertMember(JoinVO joinVO) {
		return String.valueOf((int)insert("egovframework.example.join.service.impl.JoinDAO.insertMember", joinVO));
	}
	
}
