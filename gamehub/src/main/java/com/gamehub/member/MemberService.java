package com.gamehub.member;

import javax.servlet.http.HttpSession;

import com.gamehub.domain.LoginDTO;
import com.gamehub.domain.MemberVO;

public interface MemberService {
	//회원가입
	public void registerMem(MemberVO mv)throws Exception;
	//아이디 중복체크
	public String idCheck(String mem_id)throws Exception;
	//닉네임 중복체크
	public String nickCheck(String mem_nick)throws Exception;
	//아이디 찾기
	public String searchId(LoginDTO dto)throws Exception;
	//암호 찾기
	public String searchPw(LoginDTO dto)throws Exception;
	//이메일 인증
	public void verifyMember(String mem_id)throws Exception;
	//업데이트 정보 얻어오기
	public MemberVO updateInfo(HttpSession session)throws Exception;
	//업데이트
	public void update(MemberVO mv)throws Exception;
	//아이디와 암호로 회원 조회후 일치시 모든 회원정보를 null로 없데이트
	public String delete(LoginDTO dto, HttpSession session) throws Exception;
	//로그인
	public String login(LoginDTO dto, HttpSession session)throws Exception;
	//모든 암호 암호화하기
	public void adminpw()throws Exception;
}
