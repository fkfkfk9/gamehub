$(document).ready(function(){
	$("section").css("width","97%");
	$("section").css("border","0");
	
	$('.tapItem').each(function() {
		if($(this).attr("id") == "active"){
			$('#linkdata').append($(this).data(ref));
		}
	});
	
	//리스트의 이미지 클릭시
	$('.attachImg').click(function() {
		//이미지를 반복문으로 돌린다.
		$('.attachImg').each(function() {
			$(this).attr("id", "");
		});
		$(this).attr("id", "activeImg");
		$('.mainImg').children().remove();
		var scr = "<img width='100%' height='100%' " +
				"src='"+ $(this).data("src") +"'></img>";
		$('.mainImg').append(scr);
	});
	$('.tapItem').click(function() {
		$('.tapItem').each(function() {
			$(this).attr("id", "");
		});
		$(this).attr("id", "active");
	});
	
	$(".cartBtn").click(function() {
		var jsonv = {pd_num : $(this).data("num"), ct_amount : $('#spinner').val()};
		$.ajax({
			type:"POST", 
			url:"/cart/add",
			data:jsonv, 
			success:function(data){
				if(data == "login"){
					alert("로그인을 하셔야 합니다.");
				}else if(data == "ok"){
					var stat = confirm("장바구니에 추가되었습니다.\n장바구니로 이동하시겠습니까?");
					if(stat) window.location.assign("/cart/list");
				}else{
					var stat = confirm("이미 장바구니에 있는 상품입니다.\n수량만 추가 하시겠습니까?");
					if(stat) {
						var jsonv2 = {ct_code : data, ct_amount : jsonv.ct_amount};
						$.ajax({
							type:"POST", 
							url:"/cart/update",
							data:jsonv2, 
							success:function(data){
								if(data == "ok"){
									var stat = confirm("수량이 변경 되었습니다.\n장바구니로 이동하시겠습니까?");
									if(stat) window.location.assign("/cart/list");
								}
							}//END OF 콜백함수
						});//END OF AJAX
					}
				}
			}//END OF 콜백함수
		});//END OF AJAX
	});
	
});
