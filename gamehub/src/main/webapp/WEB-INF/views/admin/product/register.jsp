<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/member/join.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/product/register.css" />
<script src="/resources/plugins/ckeditor/ckeditor.js"></script>
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
            <a href="#">상품</a>
            <a href="#">&ndash; 상품목록</a>
            <a href="/admin/product/register" class="current">&ndash; 상품추가</a>
        </aside>
        <!-- section 본문 부분 -->
        <section>
            <div><h1>상품 추가</h1></div>
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
			    <input type="text" id="pd_name" class="inputlong" name="pd_name" placeholder="제품명" required>
			    <div>
			      <p id="name-help" class="help">게임의 제목을 입력하세요.</p>
			    </div> 
			    <input type="number" id="pd_price" class="inputshort" name="pd_price" placeholder="가격" required><span class="pricespan">원</span>
			    <input type="number" id="pd_discount" class="inputshort" name="pd_discount" placeholder="할인율" required><span class="pricespan">%</span>			    
			    <span id="resultprice"></span>
			    <div>
			      <p id="price-help" class="help">상품의 가격과 할인율을 입력하세요. 숫자만 입력가능합니다.</p>
			    </div>
			    <input type="text" id="pd_develop" class="inputlong" name="pd_develop" placeholder="개발사" required>
			    <div>
			      <p id="develop-help" class="help">개발사의 이름을 입력하세요.</p>
			    </div>
			    <input type="text" id="pd_publisher" class="inputlong" name="pd_publisher" placeholder="퍼블리셔" required>
			    <div>
			      <p id="publisher-help" class="help">배포회사를 입력하세요.</p>
			    </div>
			    <h5>시스템 요구사항 (콘솔은 모든 내용을 지우고 저장하세요.)</h5>
			    <textarea id="pd_requirement" name="pd_requirement" rows="36" cols="70">
최소<br />
운영체제: <br />
프로세서: <br />
메모리: <br />
그래픽: <br />
DirectX: <br />
저장공간: <br />
사운드카드: <br />
<br />
권장 <br />
운영체제: <br />
프로세서: <br />
메모리: <br />
그래픽: <br />
DirectX: <br />
저장공간: <br />
사운드카드: <br />
			    </textarea>
			    <div>
			      <p id="requirement-help" class="help">시스템 요구사항을 입력하세요.</p>
			    </div>
			    <h5>상품정보(이미지의 너비는 100%로 설정해주세요.)</h5>
			    <textarea id="pd_content" name="pd_content" rows="18" cols="70"></textarea>
			    <div class="margindiv"></div>
			    <div id="pd_bonusdiv">
				    <label for="pd_bonus_check">
		            	<input type="checkbox" id="pd_bonus_check">
		            	예약 특전 입력
		            </label>
	            	<div id="bonus-help" class="help">
	            		<textarea id="pd_bonus" name="pd_bonus" rows="18" cols="70"></textarea>
	            	</div>
            	</div><div class="margindiv"></div>
            	<input type="text" id="pd_release" class="inputlong" name="pd_release" placeholder="2018-01-01" required>
			    <div>
			      <p id="release-help" class="help">xxxx-xx-xx의 포멧을 맞춰 발매일을 입력해주세요.</p>
			    </div> 
			    <div id="checkdiv">
			    	<input type="number" id="pd_amount" class="inputshort" name="pd_amount" placeholder="수량" required><span class="pricespan">개</span>
				    <label for="pd_korea_check">
		            	<input type="checkbox" id="pd_korea_check">
		            	한국어 지원
		            </label>
		            <label for="pd_buy_check">
		            	<input type="checkbox" id="pd_buy_check">
		            	판매여부
		            </label>
            	</div><div class="margindiv"></div>
            	<label for="mainImgdrag"><h5>메인 이미지 파일을 끌어다 첨부해주세요.(한개만 가능)</h5></label>
				        	<div class="mainImgDrop"></div>
				<label for="subImgdrag"><h5>나머지 이미지 파일을 끌어다 첨부해주세요.(여러개 가능)</h5></label>
				        	<div class="subimgDrop"></div>
				<ul class="mainImgUpload"><li><h2>메인이미지</h2></li></ul>
				<ul class="uploadedList"><li><h2>추가 이미지 리스트</h2></ul>
				<input type="hidden" name="mem_id" value="${admin.mem_id }" />
			    <input type="hidden" name="cg_code" id="cg_code" />
			    <input type="hidden" name="pd_korea" id="pd_korea" value="N" />
			    <input type="hidden" name="pd_buy" id="pd_buy" value="N" />
			    <input type="submit" class="submit" value="상품등록">
			  </form>
			</article>
        </section>
        <footer>
            <jsp:include page="../../include/footer.jsp" flush="false" />
        </footer>
	</div>
	<script src="/js/admin/product/register.js"></script>
	<script>
	var cgArray = JSON.parse('${cgList}');
	console.log(cgArray);
	
	var root = $("select[name='rootCategory']");
	
	for(var i=0;i<cgArray.length;i++){
		if(cgArray[i].level == 1)
			root.append("<option value='"+cgArray[i].cg_code+"'>"+cgArray[i].cg_name+"</option>");	
	}
	  
	//*********** 1depth카테고리 선택 후 2depth 생성 START ***********
	$(document).on("change","select[name='rootCategory']",function(){      
	    //두번째 셀렉트 박스를 삭제 시킨다.
		var first = $("select[name='firstCategory']");
		first.children().remove(); //기존 리스트 삭제
		var second = $("select[name='secondCategory']");
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
		var second = $("select[name='secondCategory']");
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
	//ck에디터
	$(function(){
		var ckeditor_config = {
			resize_enabled : false,
			enterMode : CKEDITOR.ENTER_BR,
			shiftEnterMode : CKEDITOR.ENTER_P,
			filebrowserUploadUrl : '/admin/product/imageUpload'
		};
		CKEDITOR.replace('pd_requirement', ckeditor_config);
		CKEDITOR.replace('pd_content', ckeditor_config);
		CKEDITOR.replace('pd_bonus', ckeditor_config);
	});
	</script>
</body>
</html>
