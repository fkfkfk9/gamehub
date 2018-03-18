<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/member/mypage.css" />
<title>MyPage</title>
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
        						<li class="tapItem"><a href="/member/modify">정보수정</a></li>
        						<li class="tapItem"><a href="/member/delete">회원탈퇴</a></li>
        						<li class="tapItem"><a href="#">쿠폰정보</a></li>
        						<li class="tapItem"><a href="#">주문내역</a></li>
        						<li class="tapItem"><a href="#">장바구니</a></li>
        					</ul>
        				</div>
        			</div>
        		</article>
        	</div>
        </section>   
        <!-- 하단부 -->
        <footer>
            <jsp:include page="../include/footer.jsp" flush="false" />
        </footer>
	</div>
</body>
</html>
