<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/member/join.css" />
<link rel="stylesheet" type="text/css" href="/css/member/mypage.css" />
<title>회원정보수정</title>
</head>
<body>
	<!-- css의 모든 부분을 감싸는 부분 -->
	<div id="wapper">
		<!-- header 제목부 -->
		<header>
            <jsp:include page="../include/header.jsp" flush="false" />
        </header>
        <!-- nav 메뉴 목록 -->
        <nav class="dropdownmenu">         
            <jsp:include page="../include/nav.jsp" flush="false" />
        </nav>
        <!-- section 본문 부분 -->
        <section class="mypagesection">
        	<div class="mypagewrap">
        		<div class="mypageTitle">
        			<h1>마이페이지</h1>
        		</div>
        		<article>
        			<div class="tapMenu">
        				<div class="tap">
        					<ul class="menu">
        						<li id ="active" class="tapItem"><a href="/member/modify">정보수정</a></li>
        						<li class="tapItem"><a href="/member/delete">회원탈퇴</a></li>
        						<li class="tapItem"><a href="#">쿠폰정보</a></li>
        						<li class="tapItem"><a href="#">주문내역</a></li>
        						<li class="tapItem"><a href="#">장바구니</a></li>
        					</ul>
        				</div>
        			</div>
        		</article>
        	</div>
			<article class="rgcontainer">
			  <form class="rgform" method="post">
			  	<input type="email" id="mem_id" name="mem_id" class="inputlong" readonly="readonly" value="${member.mem_id }" required>
			  	<%-- <input type="hidden"  name="mem_id" value="${member.mem_id }" > --%>
			    <input type="password" id="mem_passwd" name="mem_passwd" class="inputlong" placeholder="비밀번호" maxlength="16" required>
			    <div>
			      <p id="passwd-help" class="help">영문 소문자/숫자 특수문자 !~@#%&._-, 6자 이상 16자 이하의 패스워드를 작성해주세요. </p>
			    </div>
			    <input type="password" id="passwd_cf" placeholder="비밀번호 확인" class="inputlong" maxlength="16" required><span id="pwcheck"></span>
			    <div>
			      <p id="passwd_cf-help" class="help">위에 작성한 암호를 그대로 입력하세요. </p>
			    </div>
			    <input type="text" id="mem_name" class="inputlong" name="mem_name" value="${member.mem_name }" placeholder="이름" required>
			    <div>
			      <p id="name-help" class="help">이름을 입력해주세요.</p>
			    </div> 
			    <input type="text" maxlength="5" id="mem_addr_num" class="inputshort" value="${member.mem_addr_num }" name="mem_addr_num" placeholder="우편번호" required>
			    <div id="wrap">
				<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png"  id="btnFoldWrap" alt="접기 버튼">
				</div><div></div>
			    <input type="text" id="mem_addr_basic" class="inputlong" value="${member.mem_addr_basic }" name="mem_addr_basic" placeholder="기본주소" required><div></div>
			    <input type="text" id="mem_addr_detail" class="inputlong" value="${member.mem_addr_detail }" name="mem_addr_detail" placeholder="상세주소" required>
			    <div>
			      <p id="addr_detail-help" class="help">나머지 주소를 입력해주세요.</p>
			    </div>
			    <c:set var="tels" value="${member.mem_tel }"/>
				<c:set var="tel_array" value="${fn:split(tels,'-')}"/>
			    <input type="text" maxlength="3" id="mem_tel1" class="inputshort" value="${tel_array[0] }" name="mem_tel1" placeholder="010" required>-
			    <input type="text" maxlength="4" id="mem_tel2" class="inputshort" value="${tel_array[1] }" name="mem_tel2" required>-
			    <input type="text" maxlength="4" id="mem_tel3" class="inputshort" value="${tel_array[2] }" name="mem_tel3" required>
			    <div>
			      <p id="mem_tel-help" class="help">연락처를 입력해주세요. 숫자만 입력가능합니다.</p>
			    </div>
			    <input type="text" id="mem_nick" class="inputlong" value="${member.mem_nick }" name="mem_nick" placeholder="닉네임" maxlength="10" required><span id="nickcheck" ></span>
			    <div>
			      <p id="nick-help" class="help">별명을 2자이상 10자 이하로 입력해주세요.</p>
			    </div>
			    <label for="mem_receive">
	            	<input type="checkbox" id="receive_check"<c:if test="${member.mem_receive == 'Y' }">checked</c:if>>
	            	정보/이벤트 메일 수신에 동의합니다.
	            </label>
            	<div id="receivep"><p>※ 주문 관련 정보, 주요 공지사항 및 이벤트 당첨 안내 등은 수신 동의 여부에 관계없이 자동 발송됩니다.</p></div>
			    <input type="hidden" name="mem_receive" id="mem_receive" />
			    <input type="hidden" name="mem_tel" id="mem_tel" />
			    <input type="submit" class="submit" value="Register">
			  </form>
			</article>
        </section>
        <footer>
            <jsp:include page="../include/footer.jsp" flush="false" />
        </footer>
	</div>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script src="/js/member/modify.js"></script>
</body>
</html>
