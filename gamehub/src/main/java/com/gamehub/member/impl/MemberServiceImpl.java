package com.gamehub.member.impl;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.gamehub.domain.LoginDTO;
import com.gamehub.domain.MemberVO;
import com.gamehub.member.MemberDAO;
import com.gamehub.member.MemberService;
import com.gamehub.util.BCrypt;
import com.gamehub.util.SHA256;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	@Override
	public void registerMem(MemberVO mv) throws Exception {
		//보안을 위해 passwd를 암호화 해주는 과정
		SHA256 sha = SHA256.getInsatnce();
		String orgPass = mv.getMem_passwd();
		String shaPass = sha.getSha256(orgPass.getBytes());
		String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
		mv.setMem_passwd(bcPass);
		dao.registerMem(mv);		
	}

	@Override
	public String idCheck(String mem_id) throws Exception {
		return dao.idCheck(mem_id);
	}

	@Override
	public String nickCheck(String mem_nick) throws Exception {
		return dao.nickCheck(mem_nick);
	}

	@Override
	public void verifyMember(String mem_id) throws Exception {
		dao.verifyMember(mem_id);		
	}

	@Override
	public String login(LoginDTO dto, HttpSession session) throws Exception {
		String result = "";
		//암호화를 위한 객체 생성
		SHA256 sha = SHA256.getInsatnce();
		//입력받은 passwd를 암호화 한다.
		String shaPass = sha.getSha256(dto.getMem_passwd().getBytes());
		LoginDTO returnDTO = dao.login(dto);
		
		//DB상에 데이터가 있는지 없는지 확인
		if(returnDTO == null || returnDTO.equals(null)) {
			result = "noid";//아이디가 존재하지 않아 검색조차 되지 않음
		}else {//아이디와 일치되는 데이터가 있을경우	
			//암호화 되어있는 DB상의 암호를 가져온다.	
			String dbpasswd = returnDTO.getMem_passwd();
			//DB상 암호와 이력받은 암호의 데이터를 비교한다.
			if(BCrypt.checkpw(shaPass,dbpasswd)) {
				//로그인 정보가 일치한 상황에서 이메일 인증여부를 확인
				if(returnDTO.getMem_verify() == "Y" || returnDTO.getMem_verify().equals("Y")) {
					//고객이 로그인한 경우
					if(returnDTO.getMem_level().equals("1")) {
						//이메일 인증 통과한 계정의 경우 로그인 작업
						returnDTO.setMem_passwd(null);
						//마지막 로그인 시간 업데이트
						dao.lastLogin(returnDTO);
						session.setAttribute("loginDto", returnDTO);
						result = "login";
					}else{
						returnDTO.setMem_passwd(null);
						//마지막 로그인 시간 업데이트
						dao.lastLogin(returnDTO);
						session.setAttribute("admin", returnDTO);
						result = "admin";
					}		
				}else if(returnDTO.getMem_verify() == "X" || returnDTO.getMem_verify().equals("X")) {
					result = "noreg";
				}else {//이메일 인증이 안되어 로그인 실패
					result = "noverify";
				}				
			}else result = "nopass";//암호가 일치하지 않음
		}
		return result;
	}

	@Override
	public void adminpw() throws Exception {
		List<MemberVO> list =  dao.adminPwSe();
		SHA256 sha = SHA256.getInsatnce();
		String orgPass = "";
		String shaPass = "";
		if(list != null) {
			for(int i=0; i<list.size();i++) {
				if(list.get(i).getMem_passwd().length() < 5) {
					orgPass = list.get(i).getMem_passwd();
					shaPass = sha.getSha256(orgPass.getBytes());
					list.get(i).setMem_passwd(BCrypt.hashpw(shaPass, BCrypt.gensalt()));
					dao.adminPwUp(list.get(i));
				}
			}
		}		
	}

	@Override
	public String searchId(LoginDTO dto) throws Exception {
		String result = "";
		LoginDTO dto2 = dao.searchId(dto);
		if(dto2 == null || dto2.equals(null)) {
			result = "noid";
		}else {
			if(dto2.getMem_verify().equals("X")) {
				result = "noreg";
			}else if(dto2.getMem_verify().equals("N")) {
				result = "nomail";
			}else {
				result = dto2.getMem_id();
			}
		}		
		return result;
	}

	@Override
	public String searchPw(LoginDTO dto) throws Exception {
		String result = "";
		LoginDTO dto2 = dao.searchId(dto);
		System.out.println("DB아이디 : " + dto2.getMem_id());
		System.out.println("입력받은 아이디 : " + dto.getMem_id());
		if(dto2 == null || dto2.equals(null)) {
			result = "nosearch";
			if(dto2.getMem_verify().equals("X")) {
				result = "noreg";
			}else {
				if(dto.getMem_id().equals(dto2.getMem_id())) {
					String ranpasswd = UUID.randomUUID().toString().replaceAll("-", "");
					ranpasswd = ranpasswd.substring(0, 12);
					SHA256 sha = SHA256.getInsatnce();
					String shaPass = sha.getSha256(ranpasswd.getBytes());
					String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
					dto.setMem_passwd(bcPass);
					dao.searchPw(dto);
					System.out.println("업데이트를 끝낸 후");
					result = ranpasswd;
				}else result = "failid";	
			}			
		}
		return result;
	}

	@Override
	public MemberVO updateInfo(HttpSession session)throws Exception {
		LoginDTO dto = (LoginDTO)session.getAttribute("loginDto");
		return dao.updateInfo(dto.getMem_id());
	}

	@Override
	public void update(MemberVO mv) throws Exception {
		SHA256 sha = SHA256.getInsatnce();
		String orgPass = mv.getMem_passwd();
		String shaPass = sha.getSha256(orgPass.getBytes());
		String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
		mv.setMem_passwd(bcPass);
		
		dao.update(mv);
	}
	
	@Override
	public String delete(LoginDTO dto, HttpSession session) throws Exception {
		String result = "";
		//암호화를 위한 객체 생성
		SHA256 sha = SHA256.getInsatnce();
		//입력받은 passwd를 암호화 한다.
		String shaPass = sha.getSha256(dto.getMem_passwd().getBytes());
		LoginDTO returnDTO = dao.login(dto);
		
		//DB상에 데이터가 있는지 없는지 확인
		if(returnDTO == null || returnDTO.equals(null)) {
			result = "noid";//아이디가 존재하지 않아 검색조차 되지 않음
		}else {//아이디와 일치되는 데이터가 있을경우	
			//암호화 되어있는 DB상의 암호를 가져온다.
			String dbpasswd = returnDTO.getMem_passwd();
			//DB상 암호와 이력받은 암호의 데이터를 비교한다.
			if(BCrypt.checkpw(shaPass,dbpasswd)) {
				//암호가 같은경우 즉 입력정보가 일치했기 때문에 업데이트 진행
				dao.delete(dto.getMem_id());
				//업데이트가 진행되었는지 확인하기 위해 mem_verify를 검색해온다.
				if(dao.deleteCheck(dto.getMem_id()).equals("X")) {
					session.invalidate();
					result = "delete";
				}else {
					result = "fail";
				}								
			}else result = "nopass";//암호가 일치하지 않음
		}
		return result;
	}

}
