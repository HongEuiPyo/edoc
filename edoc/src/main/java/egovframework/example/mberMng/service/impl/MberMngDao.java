package egovframework.example.mberMng.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.example.mberMng.service.MberMngSearchVO;
import egovframework.example.mberMng.service.MberMngVO;

@Repository("mberMngDao")
public class MberMngDao extends EgovAbstractMapper {

	public List<MberMngVO> mberMngList(MberMngSearchVO mberMngSearchVO) {
		return selectList("egovframework.example.mberMng.service.impl.MberMngDao.mberMngList", mberMngSearchVO);
	}
	
	public void mberMngDetail() {
		
	}
	 
}
