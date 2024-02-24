package egovframework.example.mberMng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.example.mberMng.service.MberMngSearchVO;
import egovframework.example.mberMng.service.MberMngService;
import egovframework.example.mberMng.service.MberMngVO;

@Service("mberMngService")
public class MberMngServiceImpl extends EgovAbstractServiceImpl implements MberMngService {

	@Resource(name = "mberMngDao")
	private MberMngDao mberMngDao;
	
	@Override
	public List<MberMngVO> mberMngList(MberMngSearchVO mberMngSearchVO) {
		List<MberMngVO> resultList = mberMngDao.mberMngList(mberMngSearchVO);
		return resultList;
	}
	
	

}
