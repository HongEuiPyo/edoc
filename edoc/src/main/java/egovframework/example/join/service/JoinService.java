package egovframework.example.join.service;

public interface JoinService {

	/**
	 * 회원가입 처리
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String join(JoinVO vo) throws Exception;

}
