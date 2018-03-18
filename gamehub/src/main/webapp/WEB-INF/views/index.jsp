<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameHub</title>
</head>
<body>
	<!-- css의 모든 부분을 감싸는 부분 -->
	<div id="wapper">
		<!-- header 제목부 -->
		<header>
            <jsp:include page="./include/header.jsp" flush="false" />
        </header>
        <!-- nav 메뉴 목록 -->
        <nav class="dropdownmenu">         
            <jsp:include page="./include/nav.jsp" flush="false" />
        </nav><div id="blankdiv"></div>
        <!--aside-->
        <aside>
            <h4>공지사항</h4>
            <p>공지사항1</p>
            <p>공지사항2</p>
            <p>공지사항3</p>
            <p>공지사항4</p>
            <p>공지사항5</p>
        </aside>
        <!-- section 본문 부분 -->
        <section>
            <h4>최신게임</h4>
            <article>
                <p>최신게임1</p>
                <p>최신게임2</p>
                <p>최신게임3</p>
                <p>최신게임4</p>
                <p>최신게임5</p>
            </article>
            <h4>할인게임</h4>
            <article>
                <p>할인게임1</p>
                <p>할인게임2</p>
                <p>할인게임3</p>
                <p>할인게임4</p>
                <p>할인게임5</p>
            </article>
        </section>
        <script>
	        var result = '${msg}';
			
			if (result == 'register') {
				alert("입력하신 이메일로 회원가입 승인 메일을 전송했습니다.\n"
				+"이메일 인증이 끝나셔야 회원가입이 완료됩니다.");
			}else if(result == 'verify'){
				alert("모든 회원가입 절차를 완료하였습니다.");
			}else if(result == 'login'){
				alert("로그인에 성공하였습니다.");
			}else if(result == 'modify'){
				alert("회원정보 수정을 완료 하였습니다.");
			}else if(result == 'delete'){
				alert("회원탈퇴를 완료 하였습니다.");
			}else if(result == 'admin'){
				alert("관리자로 로그인 하였습니다.");
			}
        </script>
        <!-- 하단부 -->
        <footer>
            <jsp:include page="./include/footer.jsp" flush="false" />
        </footer>
	</div>
</body>
</html>
