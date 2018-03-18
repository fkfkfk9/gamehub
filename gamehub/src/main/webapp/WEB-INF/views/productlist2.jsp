<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="/plugins/jQuery/jquery-3.2.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

 console.log('${list}')
 console.log('${jsonList}')



 var list = '${list}';
 var arrayList = new Array('${list}');

 console.log(list);
 console.log(arrayList);

 var json = JSON.parse('${jsonList}');

 console.log(json);

</script>
</head>
<body>
<div>
전    체:	<select>
			<c:forEach items="${list}" var="list">
				<option value="${list.cg_code}">${list.cg_name}</option>
			</c:forEach>
		</select>
1depth:	<select name="rootCategory" style="width:200px">
       		<option value="">전체</option>
       	</select>
2depth: <select name="firstCategory" style="width:200px">
        	<option value="">전체</option>
    	</select>
3depth: <select name="secondCategory" style="width:200px">
        	<option value="">전체</option>
    	</select>  
</div>
<hr />
<script>
var rootCategoryArray = JSON.parse('${jsonList}');
//메인 카테고리 셋팅
var rootCategorySelectBox = $("select[name='rootCategory']");
  
for(var i=0;i<rootCategoryArray.length;i++){
	if(rootCategoryArray[i].level == 1)
	rootCategorySelectBox.append("<option value='"+rootCategoryArray[i].cg_code+"'>"+rootCategoryArray[i].cg_name+"</option>");	
}
  
//*********** 1depth카테고리 선택 후 2depth 생성 START ***********
$(document).on("change","select[name='rootCategory']",function(){      
    //두번째 셀렉트 박스를 삭제 시킨다.
    var firstCategorySelectBox = $("select[name='firstCategory']");
    firstCategorySelectBox.children().remove(); //기존 리스트 삭제
    var secondCategorySelectBox = $("select[name='secondCategory']");
    secondCategorySelectBox.children().remove(); //기존 리스트 삭제
    secondCategorySelectBox.append("<option value=''>전체</option>");
      
    //선택한 첫번째 박스의 값을 가져와 일치하는 값을 두번째 셀렉트 박스에 넣는다.
    $("option:selected", this).each(function(){
        var selectValue = $(this).val(); //main category 에서 선택한 값
        firstCategorySelectBox.append("<option value=''>전체</option>");
        for(var i=0;i<rootCategoryArray.length;i++){
            if(selectValue == rootCategoryArray[i].cg_parent){                  
            	firstCategorySelectBox.append("<option value='"+rootCategoryArray[i].cg_code+"'>"+rootCategoryArray[i].cg_name+"</option>");
            }
        }
    });      
}); 
//*********** 1depth카테고리 선택 후 2depth 생성 END ***********

//*********** 2depth카테고리 선택 후 3depth 생성 START ***********
$(document).on("change","select[name='firstCategory']",function(){      
    //세번째 셀렉트 박스를 삭제 시킨다.
    var secondCategorySelectBox = $("select[name='secondCategory']");
    secondCategorySelectBox.children().remove(); //기존 리스트 삭제
      
    //선택한 첫번째 박스의 값을 가져와 일치하는 값을 두번째 셀렉트 박스에 넣는다.
    $("option:selected", this).each(function(){
        var selectValue = $(this).val(); //main category 에서 선택한 값
        secondCategorySelectBox.append("<option value=''>전체</option>");
        for(var i=0;i<rootCategoryArray.length;i++){
            if(selectValue == rootCategoryArray[i].cg_parent){                  
            	secondCategorySelectBox.append("<option value='"+rootCategoryArray[i].cg_code+"'>"+rootCategoryArray[i].cg_name+"</option>");
            }
        }
    });      
}); 
//*********** 2depth카테고리 선택 후 3depth 생성 END ***********
</script>

</body>
</html>