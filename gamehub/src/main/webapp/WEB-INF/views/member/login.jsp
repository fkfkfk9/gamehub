<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/member/login.css" />
<title>로그인</title>
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
        <section class="loginsection">
        	<article class="loginWapper">
        	<div id="divwrap">
        		<div class="loginTitle">
            		<h3>로 그 인</h3>
            	</div>
            	<div class="loginContents">
            		<form class="loginform" method="post">
	        			<input type="text" id="mem_id" class="inputlong" name="mem_id" placeholder="ID(이메일)" value="fkfkfk9@naver.com" required>
	        			<input type="password" id="mem_passwd" name="mem_passwd" class="inputlong" value="fkfkfk" placeholder="비밀번호" maxlength="16" required>
	        			<input type="submit" class="submit" value="Login">
        			</form>      		
            	</div>
            	<div class="loginAtag">
            		<span><a id="searchIdBtn" href="#"> 아이디 찾기 </a></span>|
            		<span><a id="searchPwBtn" href="#"> 암호 찾기 </a></span>|
            		<span><a href="/member/agreePage"> 회원가입</a></span>
            	</div>
            </div>
        	</article>            
        </section>   
        <script src="/js/member/login.js"></script>
        <script>
        var result = '${msg}';

        if (result == 'noid') {
        	alert("등록되지 않은 ID입니다.");
        	$('#mem_id').focus();
        }else if(result == 'noverify'){
        	alert("이메일 인증을 완료해야 로그인이 가능합니다.\n" + "가입할 때 입력했던 이메일로 전송된 인증 메일을 확인해주세요.");
        }else if(result == 'nopass'){
        	alert("암호가 일치하지 않습니다.");
        	$('#mem_id').focus();
        }else if(result == 'noreg'){
        	alert("탈퇴한 계정입니다.");
        }
        </script>
        <!-- 하단부 -->
        <footer>
            <jsp:include page="../include/footer.jsp" flush="false" />
        </footer>
	</div>
</body>
</html>
