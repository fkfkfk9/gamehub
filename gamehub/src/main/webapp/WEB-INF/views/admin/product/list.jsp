<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/admin/product/list.css" />
<title>상품추가</title>
</head>
<body>
	<!-- css의 모든 부분을 감싸는 부분 -->
	<div id="wapper">
		<!-- header 제목부 -->
		<header>
            <jsp:include page="../../include/admin_header.jsp" flush="false" />
        </header>
        <!-- nav 메뉴 목록 -->
        <nav class="dropdownmenu">         
            <jsp:include page="../../include/admin_nav.jsp" flush="false" />
        </nav><div id="blankdiv"></div>
        <!--aside-->
        <aside>
        	<form id="searchForm" >
	            <div class="category">
					기종:	<select id="rootCategory">
					       		<option value="0">전체</option>
					    </select><br>
					플랫폼: <select id="firstCategory">
					        	<option value="0">전체</option>
					       </select><br>
					장르: <select id="secondCategory">
								<option value="0">전체</option>
					     </select>  
					<input type="hidden" id="codeval">
					<input type="hidden" id="parentval">
					<input type="hidden" id="ancestorval">
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
				<div class="pdReg">
					<p>날짜:</p>
					<input type="button" id="day" class="btns" value="하루" />
					<input type="button" id="week" class="btns"value="1주" />
					<input type="button" id="month" class="btns"value="한달" />
					<input type="button" id="every" class="btns"value="전체" />
					<c:if test="${sp.date == 0}">
					<p id="dateInfo"></p>
					</c:if>
					<c:if test="${sp.date == 1}">
					<p id="dateInfo">하루</p>
					</c:if>
					<c:if test="${sp.date == 7}">
					<p id="dateInfo">1주</p>
					</c:if>
					<c:if test="${sp.date == 30}">
					<p id="dateInfo">한달</p>
					</c:if>
				</div>
				<div class="pdBuy">
					출력여부:	<select id="display" name="display">
					       		<option value=""
					       		<c:out value="${sp.display == null?'selected':''}"/>>전체</option>
					       		<option value="Y" 
					       		<c:out value="${sp.display eq 'Y'?'selected':''}"/>>출력</option>
					       		<option value="N"
					       		<c:out value="${sp.display eq 'N'?'selected':''}"/>>미출력</option>
					    </select>					    
				</div>
				<div class="pdBtn">
					<input type="button" id="submitBtn" style="width: 180px;" value="검색">
				</div>
				<input type='hidden' name="pageNum" value='${pb.paging.pageNum}'>
		  		<input type='hidden' name="pageSize" value='${pb.paging.pageSize}'>
				<input type="hidden" name="date" id="date">
				<input type="hidden" name="cateCode" id="cateCode">
				<input type="hidden" name="searchType" id="searchType">
			</form>
        </aside>
        <!-- section 본문 부분 -->
        <section>
        	<!-- 제목부 -->
            <div class="subject"><h1>상품 목록</h1></div>
			<!-- 물품 리스트 -->  
			<article class="products">
				<div class="pdwarp">
					<h3>총 ${cnt }개의 상품</h3>
					<table>
						<thead>
							<tr>
								<th>선택</th><th>상품번호</th><th>이미지</th><th>상품명</th><th>등록자</th><th>가격</th>
								<th>재고</th><th>판매여부</th><th>수정</th><th>삭제</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${pvlist}" var="list" >
							<tr>
								<td><input type="checkbox" class="buycheck" data-num="${list.pd_num }"></td>
								<td>${list.pd_num }</td>
								<td><img width="80px" height="80px" src="/admin/product/displayFile?fileName=${list.pd_img }"></img></td>
								<td>${list.pd_name }</td>
								<td>${list.mem_id }</td>
								<c:set value="${(list.pd_price/100)*(100-list.pd_discount) }" var="price" />
								<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${price}" /></td>
								<td>${list.pd_amount }개</td>
								<td>${list.pd_buy }</td>
								<td><input type="button" class="modifyBtn" data-num="${list.pd_num }" value="수정"></td>
								<td><input type="button" class="deleteBtn" data-num="${list.pd_num }" value="삭제"></td>
							</tr>	
							<tr class="line"><td colspan="11"></td></tr>
						</c:forEach>
						</tbody>
					</table>
					<form id="approvalform" method="post" action="/admin/product/">
						<input type="hidden" id="pd_num" name="pd_num">
						<input type="hidden" id="pd_buy" name="pd_buy">
					</form>
					<div class="approval">
						<input type="button" id="okBtn" value="승인" />
						<input type="button" id="noBtn" value="취소" />
					</div>
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
		<form id="deleteForm" method="post" action="/admin/product/delete">
		  <input type='hidden' name="pd_num" id="del_pd_num">
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
            <jsp:include page="../../include/footer.jsp" flush="false" />
        </footer>
	</div>
	<script src="/js/admin/product/list.js"></script>
	<script>
	//다른 페이지에서 list페이지로 올때 메시지
	var result = '${msg}';
	
	if (result == 'productModify') {
		alert("상품정보가 수정되었습니다.");
	}else if(result == 'productDelete'){
		alert("상품이 삭제되었습니다.");
	}else if(result == 'productAdd'){
		alert("상품이 추가되었습니다.");
	}
	
	//카테고리 DB를 json으로 받아와 배열화한다.
	var cgArray = JSON.parse('${cgList}');
	//서치 페이징 데이터를 json으로 받아와 배열화한다.
	var sp = JSON.parse('${spjson}');
	console.log(cgArray);
	console.log(sp);
	console.log(sp[0].cateCode.length);
	console.log("${cateMap.get('cg_code')}");
	console.log("${cateMap.get('cg_parent')}");
	console.log("${cateMap.get('cg_ancestor')}");
	//각각 셀렉트들을 객체화
	var root = $("#rootCategory");
	var first = $("#firstCategory");
	var second = $("#secondCategory");
	var cg_code = "${cateMap.get('cg_code')}";
	var cg_parent = "${cateMap.get('cg_parent')}";
	var cg_ancestor = "${cateMap.get('cg_ancestor')}";
	
	if(cg_code) $('#codeval').val(cg_code);
	if(cg_parent) $('#parentval').val(cg_code);
	if(cg_ancestor) $('#ancestorval').val(cg_code);
	
	//카테고리 데이터 수만큼 반복
	for(var i=0;i<cgArray.length;i++){
		//1차 카테고리만 걸러낸다.
		if(cgArray[i].level == 1){
			//서치에 데이터가 없을경우 즉 검색을 안한 경우
			if(sp[0].cateCode == null)
				root.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
			//검색한 경우
			else{
				//3개 값이 모두 없는 경우는 1차 카테고리로 검색 했을 때 뿐이다 즉 코드 그대로와 일치하면 그 값을 selected로 해준다.
			 	if(cg_code == 0 && cg_parent == 0 && cg_ancestor == 0){
					if(sp[0].cateCode == cgArray[i].cg_code)
						root.append("<option value='"+cgArray[i].cg_code+"' selected>"+cgArray[i].cg_name+"</option>");
					else
						root.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
				}else if(cg_ancestor == 0){//최고조상만 없을 경우 부모코드값과 같으면 selected를 한다.
					if(cg_parent == cgArray[i].cg_code)
						root.append("<option value='"+cgArray[i].cg_code+"' selected>"+cgArray[i].cg_name+"</option>");
					else
						root.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
				}else if(cg_code != 0 && cg_parent != 0 && cg_ancestor != 0){//모든값이 있다면 최고조상과 같은때 selected를 한다.
					if(cg_ancestor == cgArray[i].cg_code)
						root.append("<option value='"+cgArray[i].cg_code+"' selected>"+cgArray[i].cg_name+"</option>");
					else
						root.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
				}
			}
		}
	}
	//1차 카테고리에서 선택한것이 있다면 카테고리를 바꾸지 않더라도 출력하게끔 한다.
	if($("#rootCategory option:selected").val() != ""){
		for(var i=0;i<cgArray.length;i++){
			//1차 카테고리에서 선택한 값과 2차 카테고리의 부모코드가 같은 경우
            if($("#rootCategory option:selected").val() == cgArray[i].cg_parent){
            	if(cg_code == 0 && cg_parent == 0 && cg_ancestor == 0){//검색한 값이 1차카테고리만 있을 때
            		first.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
            	}else if(cg_ancestor == 0){//검색한 값이 2차 카테고리까지 있을 때
					if(cg_code == cgArray[i].cg_code)
						first.append("<option value='"+cgArray[i].cg_code+"' selected>"+cgArray[i].cg_name+"</option>");
					else
						first.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
				}else if(cg_code != 0 && cg_parent != 0 && cg_ancestor != 0){//3차 카테고리까지 있을 때
					if(cg_parent == cgArray[i].cg_code)
						first.append("<option value='"+cgArray[i].cg_code+"' selected>"+cgArray[i].cg_name+"</option>");
					else
						first.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
				}    
            }//첫번째 카테고리에서 선택한 값과 2차카테고리의 부모코드가 같은 경우 IF
        }//for 카테고리 데이터만큼
	}//셀렉트한 값이 있을경우 IF
	
	//2차 카테고리에서 선택한것이 있다면 카테고리를 바꾸지 않더라도 출력하게끔 한다.
	if($("#firstCategory option:selected").val() != ""){
		for(var i=0;i<cgArray.length;i++){
			//2차 카테고리에서 선택한 값과 3차 카테고리의 부모코드가 같은 경우
            if($("#firstCategory option:selected").val() == cgArray[i].cg_parent){
            	if(cg_ancestor == 0){//검색한 값이 2차카테고리만 있을 때
            		second.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
            	}else if(cg_code != 0 && cg_parent != 0 && cg_ancestor != 0){//3차 카테고리까지 있을 때
					if(cg_code == cgArray[i].cg_code)
						second.append("<option value='"+cgArray[i].cg_code+"' selected>"+cgArray[i].cg_name+"</option>");
					else
						second.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
				}
            }//첫번째 카테고리에서 선택한 값과 2차카테고리의 부모코드가 같은 경우 IF
        }//for 카테고리 데이터만큼
	}//셀렉트한 값이 있을경우 IF
	  
	//*********** 1depth카테고리 선택 후 2depth 생성 START ***********
	$(document).on("change","#rootCategory",function(){      
	    //두번째 셀렉트 박스를 삭제 시킨다.
		first = $("#firstCategory");
		first.children().remove(); //기존 리스트 삭제
		second = $("#secondCategory");
		second.children().remove(); //기존 리스트 삭제
		second.append("<option value='0'>전체</option>");
		$('#ancestorval').val($(this).val());//1차카테 코드를 저장하는 히든값에 첫번째 셀렉트박스 선택 val을 넣어준다.
	      
	    //선택한 첫번째 박스의 값을 가져와 일치하는 값을 두번째 셀렉트 박스에 넣는다.
	    $("option:selected", this).each(function(){
	        var selectValue = $(this).val(); //main category 에서 선택한 값
			$('#cateCode').val(selectValue);
	        first.append("<option value='0'>전체</option>");
	        for(var i=0;i<cgArray.length;i++){
	            if(selectValue == cgArray[i].cg_parent){                  
	            	first.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
	            }
	        }
	    });      
	}); 
	//*********** 1depth카테고리 선택 후 2depth 생성 END ***********

	//*********** 2depth카테고리 선택 후 3depth 생성 START ***********
	$(document).on("change","#firstCategory",function(){      
	    //세번째 셀렉트 박스를 삭제 시킨다.
		var second = $("#secondCategory");
		second.children().remove(); //기존 리스트 삭제
		$('#parentval').val($(this).val());//2차카테 코드를 저장하는 히든값에 두번째 셀렉트박스 선택 val을 넣어준다.
		
	    //선택한 첫번째 박스의 값을 가져와 일치하는 값을 두번째 셀렉트 박스에 넣는다.
	    $("option:selected", this).each(function(){
	        var selectValue = $(this).val(); //main category 에서 선택한 값
	        $('#cateCode').val(selectValue);
	        second.append("<option value='0'>전체</option>");
	        for(var i=0;i<cgArray.length;i++){
	            if(selectValue == cgArray[i].cg_parent){                  
	            	second.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
	            }
	        }
	    });      
	}); 
	//*********** 2depth카테고리 선택 후 3depth 생성 END ***********
	$(document).on("change","#secondCategory",function(){
		$('#cateCode').val($(this).val());
		$('#codeval').val($(this).val());//3차카테 코드를 저장하는 히든값에 세번째 셀렉트박스 선택 val을 넣어준다.
	});
	
		//페이지 버튼 클릭시 진행될 내용
		$('.active').css('background', 'white');
		$('.active a').css('color', 'black');
		
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
