<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/product/read.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title>상품설명</title>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->

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
            <div id="titlediv"><h1>[${pv.cg_p_name}]${pv.pd_name}</h1></div>
			<article class="gallerywarp">
			  	<div class="mainImg">
			  		<img width="100%" height="100%" src="/admin/product/displayFile?fileName=${alist.get(0).a_fullname }"></img>
			  	</div>
			  	<div class="imgList">
			  		<ul>
			  		<c:set var="idx" value="1" />
			  		<c:forEach items="${alist}" var="attach">
			  			<li>
			  				<img <c:out value="${idx == 1?'id=activeImg':''}"/> data-src="/admin/product/displayFile?fileName=${attach.a_fullname}"
			  				class="attachImg" width="150px" height="100px" src="/admin/product/displayFile?fileName=${attach.a_scalname}"></img>
			  			</li>
			  			<c:set var="idx" value="${idx+1}" />
			  		</c:forEach>
			  		</ul>
			  	</div>
			  	<div class="contents">
			  		<div id="tabs">
       					<ul>
       						<li><a href="#tabs-1">게임정보</a></li>
       						<c:if test="${pv.pd_requirement != null}">
								<li><a href="#tabs-2">시스템 요구사항</a></li>
							</c:if>       						
       						<c:if test="${pv.pd_bonus != null}">
       							<li><a href="#tabs-3">게임특전</a></li>
       						</c:if>       						
       					</ul>
       					<div id="tabs-1">${pv.pd_content}</div>
	       				<c:if test="${pv.pd_requirement != null}">
	       				<div id="tabs-2">${pv.pd_requirement}</div>
	       				</c:if>
	       				<c:if test="${pv.pd_bonus != null}">
						<div id="tabs-3">${pv.pd_bonus}</div>
						</c:if>       		
       				</div>		
			  	</div>
			</article>
			<article class="productInfo">
				<div>
					<img width="100%" height="130px" src="/admin/product/displayFile?fileName=${pv.pd_img }"></img>
				</div>
				<div class="infowarp">
					<div class="pricediv">
						<p>정상가 : <del><fmt:formatNumber type="number" maxFractionDigits="3" value="${pv.pd_price}"/>원</del></p>
						<c:set value="${(pv.pd_price/100)*(100-pv.pd_discount) }" var="price" />
						<p>판매가 : <span class="pricecolor"><fmt:formatNumber type="number" maxFractionDigits="3" value="${price}"/></span>원</p>
					</div>
					<div class="margindiv"><span class="space">출시일</span><span>${pv.pd_release}</span></div>
					<div class="margindiv"><span class="space">장르</span><span>${pv.cg_name}</span></div>
					<div class="margindiv"><span class="space">퍼블리셔</span><span>${pv.pd_publisher}</span></div>
					<div class="margindiv"><span class="space">개발사</span><span>${pv.pd_develop}</span></div>
					<div class="margindiv"><span class="space">플랫폼</span><span>${pv.cg_p_name}</span></div>
					<div class="margindiv"><span class="space">언어</span><span><c:out value="${list.pd_korea eq 'Y'?'한국어':'영어'}"/></span></div>
					<div class="margindiv"><span class="space">남은수량</span><span>${pv.pd_amount}개</span></div>
				</div>
				<div class="amount">
					<p><label for="spinner"><span class="amountspan">수량:</span></label> 
					<input id="spinner" name="value" value="1"></p>
				</div>
				<div class="btndiv">
					<input type="button" class="cartBtn" data-num="${pv.pd_num }" value="장바구니">
					<input type="button" class="buyBtn" data-num="${pv.pd_num }" value="구매">
				</div>
			</article>
        </section>
		<script>
			$(function() {
				$('#spinner').css("width", "70px");
				$("#tabs").tabs();
				$(function() {
					$("#spinner").spinner({
						spin : function(event, ui) {
							if (ui.value > 10) {
								$(this).spinner("value", 10);
								alert("대량구매는 매장에 문의하세요.")
								return false;
							} else if (ui.value < 1) {
								$(this).spinner("value", 1);
								alert("1개 이하는 선택이 불가능 합니다.")
								return false;
							}
						}
					});
				});
			});
		</script>
		<footer>
            <jsp:include page="../include/footer.jsp" flush="false" />
        </footer>
	</div>
	<script src="/js/product/read.js"></script>
</body>
</html>
