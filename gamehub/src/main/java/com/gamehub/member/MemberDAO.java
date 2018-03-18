package com.gamehub.member;

import java.util.List;

import com.gamehub.domain.LoginDTO;
import com.gamehub.domain.MemberVO;

public interface MemberDAO {
	//회원가입
	public void registerMem(MemberVO mv)throws Exception;
	//아이디 중복체크
	public String idCheck(String mem_id)throws Exception;
	//닉네임 중복체크
	public String nickCheck(String mem_nick)throws Exception;
	//아이디 찾기
	public LoginDTO searchId(LoginDTO dto)throws Exception;
	//암호 찾기
	public void searchPw(LoginDTO dto)throws Exception;
	//이메일 인증
	public void verifyMember(String mem_id)throws Exception;
	//로그인
	public LoginDTO login(LoginDTO dto)throws Exception;
	//마지막 로그인 시간
	public void lastLogin(LoginDTO dto)throws Exception;
	//업데이트 정보 불러오기
	public MemberVO updateInfo(String mem_id)throws Exception;
	//업데이트
	public void update(MemberVO mv)throws Exception;
	//삭제 : 실제로 delete를 하는게 아니라 모든 정보 update로 null처리
	public void delete(String mem_id)throws Exception;
	//삭제확인용 셀렉트
	public String deleteCheck(String mem_id)throws Exception;
	//모든암호 가져오기
	public List<MemberVO> adminPwSe()throws Exception;
	//암호화 되지 않은 암호 업데이트
	public void adminPwUp(MemberVO mv)throws Exception;	
}
