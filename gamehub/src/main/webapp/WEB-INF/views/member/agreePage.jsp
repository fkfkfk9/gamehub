<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/member/join.css" />
<title>agree</title>
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
        <section>
            <!-- 페이지 제목 -->
            <div><h1>이용약관</h1></div>
            <!-- 개인정보 수집 -->
            <!-- 개인정보 약관 컨테이너 -->
            <article class="agreeWapper">
            	<!-- 개인정보 약관 제목 -->
            	<div class="agreeTitle">
            		<span><b>개인정보 수집 및 이용 (필수)</b></span>
            	</div>
            	<!-- 개인정보 약관 내용 -->
            	<div class="agreeContents">
            		<table>
            			<thead>
            				<tr><th class="agreetd">목적</th><th class="agreetd">항목</th><th>보유기간</th></tr>
            			</thead>
            			<tbody>
            				<tr>
            					<td class="agreetd">회원제 서비스 이용 / 본인확인</td>
            					<td class="agreetd">이름, 아이디, 비밀번호, 생일, 성별, 이메일, 주소, 연락처, 휴대폰</td>
            					<td>회원탈퇴 후 5일까지</td>
            				</tr>
            			</tbody>
            		</table>
            		<label for="agreecb1" class="agreelb">
            			<input type="checkbox" id="agreecb1" class="agreecb"  >
            			개인정보 수집 및 이용에 동의합니다.
            		</label>            		
            	</div>
            	<!-- 경고문 -->
            	<p>※ GameHub의 서비스 제공을 위해서 필요한 최소한의 개인정보이므로 동의를 해 주셔야 서비스를 이용하실 수 있습니다.</p>
            </article>        
            <!-- 이용약관 컨테이너 -->
            <article class="agreeWapper">
            	<!-- 이용약관 제목 -->
            	<div class="agreeTitle">
            		<span><b>이용약관 (필수)</b></span>
            	</div>
            	<!-- 이용약관 내용 -->
            	<div class="agreeContents">
            		<iframe src="/html/member/agree.html" id="agreeiframe"></iframe>
            		<label for="agreecb2" class="agreelb">
            			<input type="checkbox" id="agreecb2" class="agreecb" >
            			이용약관에 동의합니다.
            		</label>            		
            	</div>
            </article>
            <article id="atcsubmit">
            	<div class="allchecked">
	            	<label for="agreecb3">
	            		<input type="checkbox" id="agreecb3">
	            		전체동의
	            	</label>
            	</div>
            	<div class="submitBtn">
            		<input type="button" value="동의하고 가입하기" id="joinbtn" />
            	</div>
            </article>
        </section>        
        <!-- 하단부 -->
        <footer>
            <jsp:include page="../include/footer.jsp" flush="false" />
        </footer>
	</div>
	<script src="/js/member/agreePage.js"></script>
	<script>
		$("section").css("width","97%");
		$("section").css("border","0");
		$("section").css("border-top","2px solid #999");
	</script>
</body>
</html>
