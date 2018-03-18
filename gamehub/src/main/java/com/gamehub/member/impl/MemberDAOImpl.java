package com.gamehub.member.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gamehub.member.MemberDAO;
import com.gamehub.domain.LoginDTO;
import com.gamehub.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.gamehub.mapper.MemberMapper";
	
	@Override
	public void registerMem(MemberVO mv) throws Exception {
		System.out.println("가입 dao 진입");
		System.out.println(mv.toString());
		session.insert(namespace+".registerMem", mv);
	}

	@Override
	public String idCheck(String mem_id) throws Exception {
		return session.selectOne(namespace+".idCheck", mem_id);
	}

	@Override
	public String nickCheck(String mem_nick) throws Exception {
		return session.selectOne(namespace+".nickCheck", mem_nick);
	}

	@Override
	public void verifyMember(String mem_id) throws Exception {
		session.update(namespace+".verifyMember", mem_id);		
	}

	@Override
	public LoginDTO login(LoginDTO dto) throws Exception {
		return session.selectOne(namespace+".login", dto);
	}

	@Override
	public void lastLogin(LoginDTO dto) throws Exception {
		session.update(namespace+".lastlogin", dto);		
	}

	@Override
	public List<MemberVO> adminPwSe() throws Exception {
		return session.selectList(namespace+".adminpwse");
	}

	@Override
	public void adminPwUp(MemberVO mv) throws Exception {
		session.update(namespace+".adminpwup", mv);		
	}

	@Override
	public LoginDTO searchId(LoginDTO dto) throws Exception {
		return session.selectOne(namespace+".searchId", dto);
	}

	@Override
	public void searchPw(LoginDTO dto) throws Exception {
		System.out.println("dao : "+dto.toString());
		session.update(namespace+".searchPw", dto);
	}

	@Override
	public MemberVO updateInfo(String mem_id) throws Exception {
		return session.selectOne(namespace+".updateInfo", mem_id);
	}

	@Override
	public void update(MemberVO mv) throws Exception {
		System.out.println("update DAO : " + mv.toString());
		session.update(namespace+".update", mv);
	}

	@Override
	public void delete(String mem_id) throws Exception {
		session.update(namespace+".delete", mem_id);		
	}

	@Override
	public String deleteCheck(String mem_id) throws Exception {
		return session.selectOne(namespace+".deleteCheck", mem_id);
	}
}