<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminHome</title>
</head>
<body>
	<!-- css의 모든 부분을 감싸는 부분 -->
	<div id="wapper">
		<!-- header 제목부 -->
		<header>
            <jsp:include page="../include/admin_header.jsp" flush="false" />
        </header>
        <!-- nav 메뉴 목록 -->
        <nav class="dropdownmenu">         
            <jsp:include page="../include/admin_nav.jsp" flush="false" />
        </nav><div id="blankdiv"></div>
        <!--aside-->
        <aside>
            <h4>관련 페이지</h4>
            <p>메일 : fkfkfk9@naver.com</p>
            <p></p>
            <p>게시글</p>
        </aside>
        <!-- section 본문 부분 -->
        <section>
            <h4>최근 게시글</h4>
            <article>
                <p>문의사항1</p>
                <p>문의사항2</p>
                <p>문의사항3</p>
                <p>문의사항4</p>
                <p>문의사항5</p>
            </article>
            <h4>주문내역</h4>
            <article>
                <p>주문내역1</p>
                <p>주문내역2</p>
                <p>주문내역3</p>
                <p>주문내역4</p>
                <p>주문내역5</p>
            </article>
        </section>
        <script>
	        var result = '${msg}';
			
			if (result == 'register') {
				alert("입력하신 이메일로 회원가입 승인 메일을 전송했습니다.\n"
				+"이메일 인증이 끝나셔야 회원가입이 완료됩니다.");
			}
        </script>
        <!-- 하단부 -->
        <footer>
            <jsp:include page="../include/footer.jsp" flush="false" />
        </footer>
	</div>
</body>
</html>
