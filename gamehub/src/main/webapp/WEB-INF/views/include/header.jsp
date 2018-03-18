<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- css파일을 불러온다. -->
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<!-- JQuery를 불러온다. -->
<script src="/plugins/jQuery/jquery-3.2.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/js/home.js"></script>
<div id='toplogo'>
    <h1><a href="/">GameHub</a></h1>
</div>
<div id='topsearch'>                
    <form action="" method="">          
        <input type="search">               
        <button>Search</button>
    </form>                
</div>
<div id='topBtn'>
    <c:if test="${admin == null }">
    	<c:if test="${loginDto == null}">
    		<button class="topBtns" id="topLogin">로그인</button>
	    	<button class="topBtns" id="topJoin">회원가입</button>
	    	<button class="topBtns" id="topCart">장바구니</button> 
    	</c:if>
    	<c:if test="${loginDto != null}">
    		<button class="topBtns" id="topLogout">로그아웃</button>
    		<button class="topBtns" id="topMypage">마이페이지</button>
    		<button class="topBtns" id="topCart">장바구니</button> 
    	</c:if>
    </c:if>    
	<c:if test="${admin != null }">
	    <button class="topBtns" id="topLogout">로그아웃</button>
	    <button class="topBtns" id="topAdminpage">관리페이지</button>
    </c:if>
</div>    