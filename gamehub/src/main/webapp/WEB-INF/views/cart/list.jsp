<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/cart/list.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title>장바구니</title>
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
        </nav><div id="blankdiv"></div>
        <!-- section 본문 부분 -->
        <section>
			<!-- 물품 리스트 -->  
			<article class="products">
				<!-- 제목부 -->
	            <div class="subject"><h1>장바구니</h1></div>
				<table>
					<thead>
						<tr>
							<th><input type="checkbox" id="allcheck" ></th>
							<th class="imgtd">이미지</th><th>상품명</th><th>수량</th><th>가격</th><th>합계</th>
						</tr>
					</thead>
					<tbody>
					<c:set value="0" var="total" />
					<c:forEach items="${clist}" var="list" >
						<tr>
							<c:set value="${(list.pd_price/100)*(100-list.pd_discount) }" var="price" />
							<td><input type="checkbox" class="buycheck" data-num="${list.ct_code }" 
							data-price="${list.ct_amount * price }" ></td>
							<td class="imgtd"><img width="100%" src="/admin/product/displayFile?fileName=${list.pd_img }"></img></td>
							<td class="nametd"><a href="/product/read?pd_num=${list.pd_num}">${list.pd_name }</a></td>
							<td><input id="spinner" class="spinner" name="value" value="${list.ct_amount}" data-num="${list.ct_code }"></td>							
							<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${price}" /></td>
							<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${list.ct_amount * price }"/></td>
							<%-- <c:set value="${total = total + (list.ct_amount * price)}" var="total" /> --%>
						</tr>	
						<tr class="line"><td colspan="11"></td></tr>
					</c:forEach>
					</tbody>
				</table>
				<form id="approvalform" method="post" action="/admin/product/">
					<input type="hidden" id="pd_num" name="pd_num">
					<input type="hidden" id="pd_buy" name="pd_buy">
				</form>
				<div class="deletediv">
					<input type="button" id="allDelBtn" value="전체삭제" />
					<input type="button" id="selDelBtn" value="선택삭제" />
				</div>
				<div class="orderBtnDiv"><!-- <fmt:formatNumber type="number" maxFractionDigits="3" value="${total}" /> -->
					<p>총 합계 : <span class="totalspan">0</span>원</p>
					<input type="button" id="orderBtn" value="주문하기" />
				</div>
			</article>
        </section>
       
		<form id="deleteForm" method="post" action="/admin/product/delete">
		  <input type='hidden' name="pd_num" id="del_pd_num">
		</form>
        <footer>
            <jsp:include page="../include/footer.jsp" flush="false" />
        </footer>
	</div>
	<script src="/js/cart/list.js"></script>
	<script>
	$(function() {
		$('.spinner').css("width", "33px");
		$('.spinner').css("font-size", "15px");
		$(function() {
			$(".spinner").spinner({
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
					var jsonv = {ct_code:$(this).attr("data-num"), ct_amount:ui.value};
					$.ajax({
						   url:"/cart/modify",
						   type:"post",
						   data: jsonv,
						   dataType:"text",
						   success:function(result){
							   if(result == 'ok'){
								   window.location.assign('/cart/list');
							   }
						   }
					});
				}
			});
		});
	});
	
	$(".modifyBtn").click(function() {
		var str = "/admin/product/modify?pd_num=" + $(this).data("num");
		str += "&pageNum=${pb.paging.pageNum}&pageSize=${pb.paging.pageSize}";
		str += "&searchType=${sp.searchType}&keyword=${sp.keyword}";
		str += "&minPrice=${sp.minPrice}&maxPrice=${sp.maxPrice}";
		str += "&date=${sp.date}&cateCode=${sp.cateCode}";
		str += "&display=${sp.display}";
		window.location.assign(str);
	});
	$(".deleteBtn").click(function() {
		$('#del_pd_num').val($(this).data("num"));
		$('#deleteForm').submit();
	});
	</script>
</body>
</html>
