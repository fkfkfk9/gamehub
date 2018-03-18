<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/member/join.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/product/register.css" />
<script src="/resources/plugins/ckeditor/ckeditor.js"></script>
<title>상품수정</title>
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
            <a href="/admin/product/list">상품</a>
            <a href="/admin/product/list">&ndash; 상품목록</a>
            <a href="/admin/product/register" class="current">&ndash; 상품추가</a>
        </aside>
        <!-- section 본문 부분 -->
        <section>
            <div class="titlediv"><h1>상품 수정</h1>
            <a href='/admin/product/list?pageNum=${sp.pageNum}&pageSize=${sp.pageSize}&searchType=${sp.searchType}&keyword=${sp.keyword}&minPrice=${sp.minPrice}&maxPrice=${sp.maxPrice}&date=${sp.date}&cateCode=${sp.cateCode}&display=${sp.display}'
            >목록으로</a></div>
			<article class="rgcontainer">
			  <form class="rgform" method="post">
			  	<div class="category">
				기종:	<select name="rootCategory">
				       		<option value="">전체</option>
				    </select>
				플랫폼: <select name="firstCategory">
				        	<option value="">전체</option>
				       </select>
				장르: <select name="secondCategory">
				        	<option value="">전체</option>
				     </select>  
				</div>
			    <input type="text" id="pd_name" class="inputlong" name="pd_name" value="${pv.pd_name }" placeholder="제품명" required>
			    <div>
			      <p id="name-help" class="help">게임의 제목을 입력하세요.</p>
			    </div> 
			    <input type="number" id="pd_price" class="inputshort" value="${pv.pd_price }" name="pd_price" placeholder="가격" required><span class="pricespan">원</span>
			    <input type="number" id="pd_discount" class="inputshort" value="${pv.pd_discount }" name="pd_discount" placeholder="할인율" required><span class="pricespan">%</span>			    
			    <span id="resultprice"></span>
			    <div>
			      <p id="price-help" class="help">상품의 가격과 할인율을 입력하세요. 숫자만 입력가능합니다.</p>
			    </div>
			    <input type="text" id="pd_develop" class="inputlong" value="${pv.pd_develop }" name="pd_develop" placeholder="개발사" required>
			    <div>
			      <p id="develop-help" class="help">개발사의 이름을 입력하세요.</p>
			    </div>
			    <input type="text" id="pd_publisher" class="inputlong" value="${pv.pd_publisher }" name="pd_publisher" placeholder="퍼블리셔" required>
			    <div>
			      <p id="publisher-help" class="help">배포회사를 입력하세요.</p>
			    </div>
			    <h5>시스템 요구사항</h5>
			    <textarea id="pd_requirement" name="pd_requirement" rows="18" cols="70">${pv.pd_requirement }</textarea>
			    <div>
			      <p id="requirement-help" class="help">시스템 요구사항을 입력하세요.</p>
			    </div>
			    <h5>상품정보</h5>
			    <textarea id="pd_content" name="pd_content" rows="18" cols="70">${pv.pd_content }</textarea>
			    <div class="margindiv"></div>
			    <div id="pd_bonusdiv">
				    <label for="pd_bonus_check">
		            	<input type="checkbox" id="pd_bonus_check" <c:out value="${pv.pd_bonus == null?'':'checked'}" />>
		            	예약 특전 입력
		            </label>
	            	<div id="bonus-help" class="help">
	            		<textarea id="pd_bonus" name="pd_bonus" rows="18" cols="70">${pv.pd_bonus}</textarea>
	            	</div>
            	</div><div class="margindiv"></div>
            	<input type="text" id="pd_release" class="inputlong" value="${pv.pd_release }" name="pd_release" placeholder="2018-01-01" required>
			    <div>
			      <p id="release-help" class="help">xxxx-xx-xx의 포멧을 맞춰 발매일을 입력해주세요.</p>
			    </div> 
			    <div id="checkdiv">
			    	<input type="number" id="pd_amount" class="inputshort" value="${pv.pd_amount }" name="pd_amount" placeholder="수량" required><span class="pricespan">개</span>
				    <label for="pd_korea_check">
		            	<input type="checkbox" id="pd_korea_check" <c:out value="${pv.pd_korea == 'Y'?'checked':''}" />>		            	
		            	한국어 지원
		            </label>
		            <label for="pd_buy_check">
		            	<input type="checkbox" id="pd_buy_check" <c:out value="${pv.pd_buy == 'Y'?'checked':''}" />>
		            	판매여부
		            </label>
            	</div><div class="margindiv"></div>
            	<label for="mainImgdrag"><h5>메인 이미지 파일을 끌어다 첨부해주세요.(한개만 가능)</h5></label>
				        	<div class="mainImgDrop"></div>
				<label for="subImgdrag"><h5>나머지 이미지 파일을 끌어다 첨부해주세요.(여러개 가능)</h5></label>
				        	<div class="subimgDrop"></div>
				<ul class="mainImgUpload">
				<li><h2>메인이미지</h2></li></ul>
				<ul class="uploadedList"><li><h2>추가 이미지 리스트</h2></ul>
				<input type="hidden" name="mem_id" value="${admin.mem_id }" />
			    <input type="hidden" name="cg_code" id="cg_code" />
			    <input type="hidden" name="pd_korea" id="pd_korea" value="N" />
			    <input type="hidden" name="pd_buy" id="pd_buy" value="N" />
			    <input type="submit" class="submit" value="상품수정">
			  </form>
			</article>
        </section>
        <footer>
            <jsp:include page="../../include/footer.jsp" flush="false" />
        </footer>
	</div>
	<script src="/js/admin/product/register.js"></script>
	<script src="/js/admin/product/modify.js"></script>
	<script>
	//카테고리 작업
	var cgArray = JSON.parse('${cgList}');
	console.log(cgArray);
	
	var root = $("select[name='rootCategory']");
	var first = $("select[name='firstCategory']");
	var second = $("select[name='secondCategory']");
	
	//1차카테고리 생성과 선택
	for(var i=0;i<cgArray.length;i++){
		 var sel = "";
		if(cgArray[i].level == 1){
			if(cgArray[i].cg_code == '${pv.cg_ancestor}') sel = "selected";
			root.append("<option value='"+cgArray[i].cg_code+"'" + sel + ">"+cgArray[i].cg_name+"</option>");
		}
	}
	
	//2차카테고리 생성과 선택
	for(var i=0;i<cgArray.length;i++){
		 var sel = "";
		if(cgArray[i].cg_parent == '${pv.cg_ancestor}'){
			if(cgArray[i].cg_code == '${pv.cg_parent}') sel = "selected";
			first.append("<option value='"+cgArray[i].cg_code+"'" + sel + ">"+cgArray[i].cg_name+"</option>");		
		}
	}
	
	//3차카테고리 생성과 선택
	for(var i=0;i<cgArray.length;i++){
		 var sel = "";
		if(cgArray[i].cg_parent == '${pv.cg_parent}'){
			if(cgArray[i].cg_code == '${pv.cg_code}') sel = "selected";
			second.append("<option value='"+cgArray[i].cg_code+"'" + sel + ">"+cgArray[i].cg_name+"</option>");		
		}
	}
	
	$('#cg_code').val(second.val());
	
	//*********** 1depth카테고리 선택 후 2depth 생성 START ***********
	$(document).on("change","select[name='rootCategory']",function(){      
	    //두번째 셀렉트 박스를 삭제 시킨다.
		first.children().remove(); //기존 리스트 삭제
		second.children().remove(); //기존 리스트 삭제
		second.append("<option value=''>전체</option>");
	      
	    //선택한 첫번째 박스의 값을 가져와 일치하는 값을 두번째 셀렉트 박스에 넣는다.
	    $("option:selected", this).each(function(){
	        var selectValue = $(this).val(); //main category 에서 선택한 값
	        first.append("<option value=''>전체</option>");
	        for(var i=0;i<cgArray.length;i++){
	            if(selectValue == cgArray[i].cg_parent){                  
	            	first.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
	            }
	        }
	    });      
	}); 
	//*********** 1depth카테고리 선택 후 2depth 생성 END ***********

	//*********** 2depth카테고리 선택 후 3depth 생성 START ***********
	$(document).on("change","select[name='firstCategory']",function(){      
	    //세번째 셀렉트 박스를 삭제 시킨다.
		second.children().remove(); //기존 리스트 삭제
	      
	    //선택한 첫번째 박스의 값을 가져와 일치하는 값을 두번째 셀렉트 박스에 넣는다.
	    $("option:selected", this).each(function(){
	        var selectValue = $(this).val(); //main category 에서 선택한 값
	        second.append("<option value=''>전체</option>");
	        for(var i=0;i<cgArray.length;i++){
	            if(selectValue == cgArray[i].cg_parent){                  
	            	second.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");
	            }
	        }
	    });      
	}); 
	//*********** 2depth카테고리 선택 후 3depth 생성 END ***********
	$(document).on("change","select[name='secondCategory']",function(){
		$('#cg_code').val($(this).val());
	});
	//카테고리 작업 END--------------------------------------------------------
	
	//ck에디터
	$(function(){
		var ckeditor_config = {
			resize_enabled : false,
			enterMode : CKEDITOR.ENTER_BR,
			shiftEnterMode : CKEDITOR.ENTER_P,
			filebrowserUploadUrl : '/admin/product/imageUpload'
		};
		CKEDITOR.replace('pd_content', ckeditor_config);
		CKEDITOR.replace('pd_bonus', ckeditor_config);
	});
	//ck에디터 END---------------------------------------------------------------
	
	 //이미지 작업
	 mainUploadData = '${pv.pd_img}';
	 var atlist = JSON.parse('${avo}');
	 
	 var str ="";
	 
	 //메인이미지의 데이터 타입을 체크
	 if(checkImageType(mainUploadData)){//이미지 타입의 파일일 경우
	 	//이미지를 보여주는 구문을 생성
		str ="<div><input type='hidden' name='pd_img' value='"+getImageLink(mainUploadData)+"'>"
			  +"<img  src='/admin/product/displayFile?fileName="+changeHangle(mainUploadData)+"'/><small data-src="+mainUploadData+">"
			  +"<img width='20px' height='20px' src='/img/deleteicon.png'></small></div>";
	 }else{//이미지 타입이 아닌경우
	  str = "<div><input type='hidden' value='"+mainUploadData+"'>" 
	  		  + "<span><img width='60px' height'60px' src='/img/default.png'/>"  
	  		  + getOriginalName(changeHangle(mainUploadData))+"</span><small data-src="+mainUploadData+">"
			  +"<img width='20px' height='20px' src='/img/deleteicon.png'></small></div>";
	 }
	 $(".mainImgUpload").append(str);//메인이미지 리스트에 추가
	 mainImgstat = true;
	 atlist.forEach(function(al) {
		 if(checkImageType(al.a_fullname)){//이미지 타입의 파일일 경우
			  str ="<div><input type='hidden' class='delbtn' value='"+al.a_fullname+"'>"
					  +"<img  src='/admin/product/displayFile?fileName="+changeHangle(al.a_scalname)+"'/><small data-src="+changeHangle(al.a_fullname)+">"
					  +"<img width='20px' height='20px' src='/img/deleteicon.png'></small></div>";
		  }else{//이미지 타입이 아닌경우
			  str = "<div><input type='hidden' class='delbtn' value='"+al.a_fullname+"'>" 
			  		  + "<span><img width='60px' height'60px' src='/img/default.png'/>"  
			  		  + changeHangle(al.a_filename)+"</span><small data-src="+al.a_filename+">"
					  +"<img width='20px' height='20px' src='/img/deleteicon.png'></small></div>";
		  }
		  $(".uploadedList").append(str);
	 });
		
	//이미지 작업END -------------------------------------------------------------
	</script>
</body>
</html>
