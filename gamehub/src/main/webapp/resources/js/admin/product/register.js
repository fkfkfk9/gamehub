var mainImgstat = false;

$(document).ready(function(){
	
	$("#pd_name").focus(function(){
		$("#name-help").slideDown(500);
	}).blur(function(){
		$("#name-help").slideUp(500);
	});//END OF 제품명
	
	$("#pd_price").focus(function(){
		$("#price-help").slideDown(500);
	});
	$("#pd_discount").blur(function(){
		$("#price-help").slideUp(500);
		var price = $("#pd_price").val();
		var discount = $("#pd_discount").val();
		var result = (price/100)*(100-discount);
		$("#resultprice").text("최종가격 : " + result + "원");
	});//END OF 가격
	
	$("#pd_develop").focus(function(){
		$("#develop-help").slideDown(500);
	}).blur(function(){
		$("#develop-help").slideUp(500);
	});//END OF 개발사
	
	$("#pd_publisher").focus(function(){
		$("#publisher-help").slideDown(500);
	}).blur(function(){
		$("#publisher-help").slideUp(500);
	});//END OF 퍼블리셔
	
	$("#pd_requirement").focus(function(){
		$("#requirement-help").slideDown(500);
	}).blur(function(){
		$("#requirement-help").slideUp(500);
	});//END OF 시스템 사양
	
	$('#pd_bonus_check').click(function(){
		if($('#pd_bonus_check').is(':checked')){
			$('#bonus-help').slideDown(500);
		}else{
			$('#bonus-help').slideUp(500);
		}
	});//예약 특전
	
	$('#pd_korea_check').click(function(){
		if($('#pd_korea_check').is(':checked')){
			$('#pd_korea').val("Y");
		}else{
			$('#pd_korea').val("N");
		}
	});//한국어 지원여부 클릭시 히든값에 벨류값 삽입
	
	$('#pd_buy_check').click(function(){
		if($('#pd_buy_check').is(':checked')){
			$('#pd_buy').val("Y");
		}else{
			$('#pd_buy').val("N");
		}
	});//판매여부 클릭시 히든값에 벨류값 삽입
	
	$("#pd_release").focus(function(){
		$("#release-help").slideDown(500);
	}).blur(function(){
		$("#release-help").slideUp(500);
	});//END OF 발매일
	
	$(".mainImgDrop").on("dragenter dragover", function(event){
		event.preventDefault();
	});//메인이미지의 드래그 이벤트를 지워준다.
	
	$(".subimgDrop").on("dragenter dragover", function(event){
		event.preventDefault();
	});//서브이미지의 드래그 이벤트를 지워준다.
	
	//파일을 mainImgDrop에 이미지 드랍시
	$(".mainImgDrop").on("drop", function(event){
		event.preventDefault();
		if(!mainImgstat){
			var files = event.originalEvent.dataTransfer.files;//다시확인
			var file = files[0];
			var formData = new FormData();
			formData.append("file", file);
			
			$.ajax({
				url : '/admin/product/uploadAjax',//파일을 컨트롤러로 전송
				data : formData,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data){//저장된 파일경로를 data로 받아옴
					var str ="";
					  if(checkImageType(data)){//이미지 타입의 파일일 경우
						  str ="<div><input type='hidden' name='pd_img' value='"+getImageLink(data)+"'>"
								  +"<img  src='/admin/product/displayFile?fileName="+changeHangle(data)+"'/><small data-src="+data+">"
								  +"<img width='20px' height='20px' src='/img/deleteicon.png'></small></div>";
					  }else{//이미지 타입이 아닌경우
						  str = "<div><input type='hidden' value='"+data+"'>" 
						  		  + "<span><img width='60px' height'60px' src='/img/default.png'/>"  
						  		  + getOriginalName(changeHangle(data))+"</span><small data-src="+data+">"
								  +"<img width='20px' height='20px' src='/img/deleteicon.png'></small></div>";
					  }
					  $(".mainImgUpload").append(str);
					  mainImgstat = true;
				}//전송을 선공했을시에 작업
			});//파일을 서버로 전송하기 위한 ajax
		}//메인이미지 유무 체크 없는 경우
		else{//메인이미지 있는 경우
			alert("메인이미지는 하나만 업로드 가능합니다.");
		}		
	});//파일을 드랍했을시 동작
	
	//메인이미지 말고 attach에 저장될 파일들
	$(".subimgDrop").on("drop", function(event){
		event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;//다시확인
		var file = files[0];
		var formData = new FormData();
		formData.append("file", file);
		
		$.ajax({
			url : '/admin/product/uploadAjax',//파일을 컨트롤러로 전송
			data : formData,
			dataType : 'text',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(data){//저장된 파일경로를 data로 받아옴
				var str ="";
				  if(checkImageType(data)){//이미지 타입의 파일일 경우
					  str ="<div><input type='hidden' class='delbtn' value='"+getImageLink(data)+"'>"
							  +"<img  src='/admin/product/displayFile?fileName="+changeHangle(data)+"'/><small data-src="+data+">"
							  +"<img width='20px' height='20px' src='/img/deleteicon.png'></small></div>";
				  }else{//이미지 타입이 아닌경우
					  str = "<div><input type='hidden' class='delbtn' value='"+data+"'>" 
					  		  + "<span><img width='60px' height'60px' src='/img/default.png'/>"  
					  		  + getOriginalName(changeHangle(data))+"</span><small data-src="+data+">"
							  +"<img width='20px' height='20px' src='/img/deleteicon.png'></small></div>";
				  }
				  $(".uploadedList").append(str);
			}//전송을 선공했을시에 작업
		});//파일을 서버로 전송하기 위한 ajax
	});//파일을 드랍했을시 동작
	
	//메인이미지 업로드 삭제처리
	$(".mainImgUpload").on("click", "small", function(event){
		  var that = $(this);
		  $.ajax({
			   url:"/admin/product/deleteFile",
			   type:"post",
			   data: {fileName:$(this).attr("data-src")},
			   dataType:"text",
			   success:function(result){
				   if(result == 'deleted'){
					   that.parent("div").remove();
					   mainImgstat = false;
				   }
			   }
		  });
	});
	//업로드 삭제처리
	$(".uploadedList").on("click", "small", function(event){
		  var that = $(this);
		  $.ajax({
			   url:"/admin/product/deleteFile",
			   type:"post",
			   data: {fileName:$(this).attr("data-src")},
			   dataType:"text",
			   success:function(result){
				   if(result == 'deleted'){
					   that.parent("div").remove();
				   }
			   }
		  });
	});
	//서브밋 전에 작업
	$(".rgform").submit(function(){
		var that = $(this);
		var str = "";
		$(".uploadedList .delbtn").each(function(index){
			str += "<input type='hidden' name='files["+index+"]' value='"+$(this).val()+"'>";
		});
		that.append(str);
		return true;
	});
});

//파일 포멧이 이미지타입인지 체크한다.
function checkImageType(fileName){	
	var pattern = /jpg|gif|png|jpeg/i;	
	return fileName.match(pattern);	
}

//오리지널 파일네임을 가져온다.
function getOriginalName(fileName){	
	if(checkImageType(fileName)){
		return;
	}	
	var idx = fileName.indexOf("_") + 1 ;
	return fileName.substr(idx);	
}

//현재의 스케일된 파일이 아닌 실제 이미지의 이름을 리턴해준다. 즉 s_를 빼고 리턴
function getImageLink(fileName){	
	if(!checkImageType(fileName)){//이미지 타입이 아닐경우 스케일을 안하기 때문에 리턴
		return;
	}
	var front = fileName.substr(0,13);//경로부터 s_전까지의 부분을 자른다.
	var end = fileName.substr(15);	//s_다음부분을 자른다.
	return front + end;	//자른 두부분을 더해서 리턴해준다.
}

//한글파일명 인코딩
function changeHangle(key){	
	return encodeURIComponent(key);	
}