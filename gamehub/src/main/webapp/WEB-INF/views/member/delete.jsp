<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/member/mypage.css" />
<link rel="stylesheet" type="text/css" href="/css/member/join.css" />
<title>회원탈퇴</title>
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
        						<li class="tapItem"><a href="/member/modify">정보수정</a></li>
        						<li id ="active" class="tapItem"><a href="/member/delete">회원탈퇴</a></li>
        						<li class="tapItem"><a href="#">쿠폰정보</a></li>
        						<li class="tapItem"><a href="#">주문내역</a></li>
        						<li class="tapItem"><a href="#">장바구니</a></li>
        					</ul>
        				</div>
        			</div>
        		</article>
        		<article id="deletewrap">
        			<div class="deleteform">
        				<div id="warning">
        					<p>-보유하고 계신 적립금, 쿠폰이 모두 삭제되어 복구가 불가능하여, 탈퇴 후 재가입하셔도 적립금이 재발급 되지 않습니다.</p>
        					<p>-삭제된 계정의 ID로는 재가입이 불가능 합니다.</p>
        					<p>-회원탈퇴 버튼을 누른 뒤에는 복구가 불가능 하오니 심사숙고하여 결정하시기 바랍니다.</p>
        				</div>
        				<div id="deletecontents">
        					<form method="post">
			        			<input type="text" id="mem_id" class="inputlong" name="mem_id" readonly="readonly" value="${loginDto.mem_id }" >
			        			<input type="password" id="mem_passwd" name="mem_passwd" class="inputlong" placeholder="비밀번호" maxlength="16" required>
			        			<input type="submit" class="submit" value="회원탈퇴">
        					</form>   
        				</div>
        			</div>
        		</article>
        	</div>
        </section>   
        <script>
        var result = '${msg}';

        if(result == 'nopass'){
        	alert("암호가 일치하지 않습니다.");
        	$('#mem_passwd').focus();
        }else if(result == 'fail' || result == 'noid'){
        	alert("서버가 불안정하여 처리가 되지 않았습니다.\n"+"나중에 다시 시도하세요.");
        }
        </script>
        <!-- 하단부 -->
        <footer>
            <jsp:include page="../include/footer.jsp" flush="false" />
        </footer>
	</div>
</body>
</html>