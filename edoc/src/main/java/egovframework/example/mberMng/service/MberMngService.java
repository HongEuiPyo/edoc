package egovframework.example.mberMng.service;

import java.util.List;



public interface MberMngService {

	public List<MberMngVO> mberMngList(MberMngSearchVO mberMngSearchVO);

	public MberMngVO selectMberMngDetail();

	public MberMngVO insertMberMng(MberMngVO mberMngVo);

	public MberMngVO updateMberMng(MberMngVO mberMngVo);

	public MberMngVO deleteMberMng(MberMngVO mberMngVo);

	public int mberMngVOListTotCnt(MberMngSearchVO mberMngSearchVO);

}
