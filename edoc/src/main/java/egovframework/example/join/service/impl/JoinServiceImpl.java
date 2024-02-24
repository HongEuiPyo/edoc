package egovframework.example.join.service.impl;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.example.join.service.JoinService;
import egovframework.example.join.service.JoinVO;

@Service("joinService")
public class JoinServiceImpl extends EgovAbstractServiceImpl implements JoinService {

	@Resource(name = "joinDAO")
	private JoinDAO joinDAO;
	
	@Resource(name = "egovMemberIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Override
	public String join(JoinVO joinVO) throws Exception {
		// 고유아이디 셋팅
		String uniqId = idgenService.getNextStringId();
		joinVO.setUniqId(uniqId);
		
		String result = joinDAO.insertMember(joinVO);
		
		return result;
	}
	
}
