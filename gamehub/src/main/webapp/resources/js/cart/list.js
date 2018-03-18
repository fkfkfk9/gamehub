$(document).ready(function(){
	$("section").css("width","97%");
	
	$('#allcheck').click(function() {
		if($(this).is(':checked')){
			var total = 0;
			$('.buycheck').each(function() {
				this.checked = true;
				total = total + parseInt($(this).attr("data-price"));
			});
			$('.totalspan').text("");
			$('.totalspan').append(comma(total));
		}else{
			$('.buycheck').each(function() {
				this.checked = false;
			});
			$('.totalspan').text("");
			$('.totalspan').append(0);
		}		
	});

	$('.buycheck').click(function() {
		var total = 0;
		$('.buycheck').each(function() {
			if($(this).is(':checked')){
				total = total + parseInt($(this).attr("data-price"));
			}
		});
		$('.totalspan').text("");
		$('.totalspan').append(comma(total));
	});
	
	//장바구니 선택삭제
	$('#selDelBtn').click(function() {
		var str = "";
		$('.buycheck').each(function() {
			if($(this).is(':checked')){
				str += $(this).data('num') + ",";				
			}
		});
		$.ajax({
		   url:"/cart/delete",
		   type:"post",
		   data: {cclist:str.slice(0,-1)},
		   dataType:"text",
		   success:function(result){
			  if(result == "nodata"){
				  alert("삭제할 상품을 선택하지 않았습니다.");
			  }else if(result == "ok"){
				  alert("삭제가 완료되었습니다.");
				  window.location.assign('/cart/list');
			  }
		   }
		 });
	});
	//장바구니 전체 삭제
	$('#allDelBtn').click(function() {
		var str = "";
		$('.buycheck').each(function() {
			str += $(this).data('num') + ",";				
		});
		$.ajax({
		   url:"/cart/delete",
		   type:"post",
		   data: {cclist:str.slice(0,-1)},
		   dataType:"text",
		   success:function(result){
			  if(result == "nodata"){
				  alert("장바구니에 상품이 없습니다.");
			  }else if(result == "ok"){
				  alert("삭제가 완료되었습니다.");
				  window.location.assign('/cart/list');
			  }
		   }
		 });
	});
});

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

