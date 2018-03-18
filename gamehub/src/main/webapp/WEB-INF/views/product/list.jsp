<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/product/list.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title>상품목록</title>
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
        <!--aside-->
        <aside>
        	<form id="searchForm" >
        		<div class="category">
					장르: <select id="childcategory">
						<option value="">전체</option>
							<c:forEach items="${catelist}" var="cl">
								<option value="${cl.cg_code}">${cl.cg_name}</option>
								<c:if test="${cl.cg_code == sp.cateCode}">
									<option value="${cl.cg_code}" selected>${cl.cg_name}</option>
								</c:if>
							</c:forEach>
				     	</select>  
				</div>
				<div class="pdName">
					<p>검색어:</p>
					<c:if test="${sp.keyword != null}">
						<input type="text" name="keyword" id="keyword" class="inputshort" value="${sp.keyword}" placeholder="DARK SOULS">	
					</c:if>
					<c:if test="${sp.keyword == null}">
						<input type="text" name="keyword" id="keyword" class="inputshort" placeholder="DARK SOULS">	
					</c:if> 
				</div>
				<div class="pdPrice">
					<p>최소가격:</p>
					<c:if test="${sp.minPrice != 0}">
						<input type="number" name="minPrice" id="minPrice" class="inputshort" placeholder="10000" value="${sp.minPrice}">~	
					</c:if>
					<c:if test="${sp.minPrice == 0}">
						<input type="number" name="minPrice" id="minPrice" class="inputshort" placeholder="10000">~	
					</c:if>
					<p>최대가격:</p>
					<c:if test="${sp.maxPrice != 0}">
						<input type="number" name="maxPrice" id="maxPrice" class="inputshort" placeholder="60000" value="${sp.maxPrice}">~	
					</c:if>
					<c:if test="${sp.maxPrice == 0}">
						<input type="number" name="maxPrice" id="maxPrice" class="inputshort" placeholder="60000">~	
					</c:if>
				</div>
				<div class="pdBtn">
					<input type="button" id="submitBtn" style="width: 180px;" value="검색">
				</div>
				<input type='hidden' name="pageNum" value='${pb.paging.pageNum}'>
		  		<input type='hidden' name="pageSize" value='${pb.paging.pageSize}'>
				<input type="hidden" name="cateCode" id="cateCode">
				<input type="hidden" name="display" id="display" value="Y">
				<input type="hidden" name="searchType" id="searchType" value="${sp.searchType}" data-st="${sp.searchType}">
			</form>
        </aside>
        <!-- section 본문 부분 -->
        <section>
        	<!-- 제목부 -->
            <div class="subject"><h1>상품 목록</h1></div>
            <!-- icon부 -->
            <div class="icon">            
            	<div class="iconimg">
            	<a href="/product/list?pageNum=${pb.paging.pageNum}&pageSize=${pb.paging.pageSize}&searchType=${sp.searchType}&keyword=${sp.keyword}&minPrice=${sp.minPrice}&maxPrice=${sp.maxPrice}&display=${sp.display}&cateCode=${sp.cateCode}">
            	<img id="list"></a></div>            		
            	<div class="iconimg">
            	<a href="/product/cube?pageNum=${pb.paging.pageNum}&pageSize=${pb.paging.pageSize}&searchType=${sp.searchType}&keyword=${sp.keyword}&minPrice=${sp.minPrice}&maxPrice=${sp.maxPrice}&display=${sp.display}&cateCode=${sp.cateCode}">
            	<img id="cube"></a></div>            	
            </div>
			<!-- 물품 리스트 -->  
			<article class="products">
				<div class="pdwarp">
					<h3>총 ${cnt }개의 상품</h3>
					<table>
						<tbody>
						<c:forEach items="${pvlist}" var="list" >
							<tr class="listtr">
								<td class="imgtd" rowspan="2">
								<a href="/product/read?pd_num=${list.pd_num}">
								<img width="100%" height="100%" src="/admin/product/displayFile?fileName=${list.pd_img }"></img></a></td>
								<td class="infotd">
								<a href="/product/read?pd_num=${list.pd_num}">
								[${list.cg_p_name}] ${list.pd_name }</a></td>
								<td rowspan="2"><span class="discount">${list.pd_discount}%</span></td>
								<td><p>정상가 : <del><fmt:formatNumber type="number" maxFractionDigits="3" value="${list.pd_price}"/></del></p></td>
								<td>
									<div class="amount">
										<p><label for="spinner">수량:</label> 
										<input id="spinner" class="spinner" name="value" value="1" data-num="${list.pd_num }"></p>
									</div>
								</td>
							</tr>
							<tr class="listtr">
							<td class="infotd">
								<span class="tagspan">${list.cg_name}</span>
								<span class="tagspan"><c:out value="${list.pd_korea eq 'Y'?'한국어':'영어'}"/></span>
								<span class="tagspan">${list.pd_publisher}</span></td>
								<c:set value="${(list.pd_price/100)*(100-list.pd_discount) }" var="price" />								
								<td><p>판매가 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${price}"/></p></td>
								<td class="btntd"><input type="button" class="cartBtn" data-num="${list.pd_num }" data-amount="1" value="장바구니 담기">
								<input type="hidden" id="" value="1"></td>
							</tr>	
							<tr class="line"><td colspan="11"></td></tr>
						</c:forEach>
						</tbody>
					</table>
					<form id="approvalform" method="post" action="/admin/product/">
						<input type="hidden" id="pd_num" name="pd_num">
						<input type="hidden" id="pd_buy" name="pd_buy">
					</form>
					<div class="pagingbtn">				
						<ul class="pagination">
							<c:if test="${pb.prev}">
								<li><a href="${pb.startPage - 1}">이전</a></li>
							</c:if>
							<c:forEach begin="${pb.startPage }"
								end="${pb.endPage }" var="idx">
								<li
									<c:out value="${pb.paging.pageNum == idx?'class =active':''}"/>>
									<a href="${idx}"> ${idx} </a>
								</li>
							</c:forEach>
							<c:if test="${pb.next && pb.endPage > 0}">
								<li><a
									href="${pb.endPage +1}">다음</a></li>
							</c:if>
						</ul><!-- end of  pagination-->
					</div><!-- end of  pagingbtn-->
				</div><!-- end of  pdwarp-->
			</article>
        </section>
        <!-- 페이지 버튼 클릭시 Controller로 정보를 보내기 위한 form태그 -->
        <form id="PagingForm">
		  <input type='hidden' name="pageNum" value='${pb.paging.pageNum}'>
		  <input type='hidden' name="pageSize" value='${pb.paging.pageSize}'>
		  <input type='hidden' name="searchType" value='${sp.searchType}'>
		  <input type='hidden' name="keyword" value='${sp.keyword}'>
		  <input type='hidden' name="minPrice" value='${sp.minPrice}'>
		  <input type='hidden' name="maxPrice" value='${sp.maxPrice}'>
		  <input type='hidden' name="date" value='${sp.date}'>
		  <input type='hidden' name="cateCode" value='${sp.cateCode}'>
		  <input type='hidden' name="display" value='${sp.display}'>
		</form>
        <footer>
            <jsp:include page="../include/footer.jsp" flush="false" />
        </footer>
	</div>
	<script src="/js/product/list.js"></script>
	<script>
	$(function() {
		$('.spinner').css("width", "20px");
		$('.spinner').css("font-size", "13px");
		$(function() {
			$(".spinner").spinner({
				spin : function(event, ui) {
					//선택한 시점의 물품 번호를 받아온다.
					var num = $(this).data("num");
					//모든 장바구니 번호를 조회한다.
					$('.cartBtn').each(function() {
						//선택한점과 같은 물품번호를 찾아 수량 데이터를 변형해준다.
						if($(this).data("num") == num){
							$(this).data("num")
							$(this).attr("data-amount", ui.value);
						}
					});
		
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
	
	//다른 페이지에서 list페이지로 올때 메시지
	var result = '${msg}';
	
	if (result == 'productModify') {
		alert("상품정보가 수정되었습니다.");
	}
	
	//페이지 버튼 클릭시 진행될 내용
	$('.active').css('background', 'white');
	$('.active a').css('color', 'black');
	
	var cateCode = "${sp.cateCode}";
	if(cateCode){
		$('#cateCode').val("${sp.cateCode}");
	}
	
	</script>
</body>
</html>
