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
            <h4>관련 페이지</h4>
            <p>개발자 메일 :</p>
            <p>fkfkfk9@naver.com</p>
            <p>소스코드 :</p>
            <p><a href="https://github.com/fkfkfk9/gamehub">https://github.com/fkfkfk9/gamehub</a></p>
            <p>작업중인 블로그 :</p>
            <p><a href="https://fkfkfk9.github.io/">https://fkfkfk9.github.io/</a></p>
            <p>관리자 계정 테스트시</p>
            <p>id : admin, pw: 1111</p>
        </aside>
        <!-- section 본문 부분 -->
        <section>
            <h2>실제 쇼핑몰이 아닌 Spring프로젝트 테스트용 게임 쇼핑몰 페이지 입니다.</h2>
            <article>
            	<h4>개발 환경</h4>
                <p>서버 : AWS EC2 서버, WAS : tomcat8, 개발환경 : spring framework, DB : 오라클 11g </p>
                <h4>진행된 작업</h4>
                <p>1.회원 : 회원가입, 회원 로그인, 회원정보 수정, 회원탈퇴</p>
                <p>2.상품(관리자) : 상품 추가, 상품 목록, 상품 수정, 상품 삭제</p>
                <p>3.상품(회원) : 카테고리 별 상품 리스트, 상품 바둑판식 리스트식 정렬 변경가능, 상품 상세 페이지</p>
                <p>4.장바구니 : 상품 리스트, 상세 페이지에서 장바구니 추가, 추가시 중복 데이터 업데이트, 장바구니 페이지에서 추가,수정,삭제</p>
                <h4>주요 기능</h4>
                <p>1.id중복 체크 등에서 사용된 ajax로 중복 데이터 체크 및 jquery로 결과 표시</p>
                <p>2.회원가입, 로그인, 회원정보 수정 등 BCrypt와 SHA256을 이용한 패스워드 암호화 작업을 통해 보안성 강화 </p>
                <p>3.상품 등록에서 사용한 여러개의 이미지 파일을 CK에디터 업로드 및 드레그로 업로드 한 후 DB에 저장하여 관리</p>
                <p>4.관리자가 좀 더 편하게 상품 관리할 수 있도록 5가지 옵션의 검색기능을 제공</p>
                <p>5.관리자 상품목록과 장바구니 등 체크박스 선택을 통해 특정 데이터만 DB에서 처리</p>                
                <h4>진행 예정 작업</h4>
                <p>1.장바구니에서 선택된 상품을 구매페이지로 전송, 상품 상세 페이지에서 구매 페이지로 전송</p>
                <p>2.상품 구매 기능</p>
                <p>3.주문내역 페이지와 주문 상세 내역 추가</p>
                <p>4.인터셉터를 추가하여 비로그인시 접근 불가한 페이지 설정하기</p>
                <p>5.게시판 기능(미정)</p>
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
