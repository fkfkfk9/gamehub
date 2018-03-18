$(document).ready(function(){
	
	//페이지 버튼
	$(".pagination li a").on("click", function(event){
		
		event.preventDefault(); 

		var targetPage = $(this).attr("href");
		
		var pageingForm = $("#PagingForm");
		pageingForm.find("[name='pageNum']").val(targetPage);
		pageingForm.attr("action","/product/list").attr("method", "get");
		pageingForm.submit();
	});
	
	$(document).on("change","#childcategory",function(){
		$('#cateCode').val($(this).val());
	});
	
	$("#submitBtn").click(function() {
		var str = "";
		if($('#cateCode').val() != ""){
			if($('#searchType').val().slice(0,4) == 'ctg1' && $('#childcategory').val() != "") str = "ctg2_";
			else if($('#searchType').val().slice(0,4) == 'ctg1' && $('#childcategory').val() == "") str = "ctg1_";
			else if($('#searchType').val().slice(0,4) == 'ctg2' && $('#childcategory').val() != "") str = "ctg3_";
			else if($('#searchType').val().slice(0,4) == 'ctg2' && $('#childcategory').val() == "") str = "ctg2_";
			else str = $('#searchType').data("st").slice(0,5);
		}
		str += "pb_";
		if($('#keyword').val()){
			var key = $('#keyword').val();
			var eckey = encodeURIComponent(key);
			$('#keyword').val(eckey);
			str += "pn_";		
		}
		if(($('#minPrice').val() != "" || $('#minPrice').val() != 0) && ($('#maxPrice').val() == "" || $('#maxPrice').val() == 0)){
			$('#maxPrice').val("0");
			str += "ppmin_";
		}
		else if(($('#minPrice').val() == "" || $('#minPrice').val() == 0) && ($('#maxPrice').val() != "" || $('#maxPrice').val() != 0)){
			$('#minPrice').val("0");
			str += "ppmax_";
		}
		else if(($('#minPrice').val() != "" || $('#minPrice').val() != 0) && ($('#maxPrice').val() != "" || $('#maxPrice').val() != 0))
			str += "pp_";
		else{
			$('#maxPrice').val("0");
			$('#minPrice').val("0");
		}
		$('#searchType').val(str.slice(0,-1));
		//alert($('#searchType').val());
		//$('#searchForm').attr('method','');
		$('#searchForm').submit();
	});	
	$(".cartBtn").click(function() {
		var jsonv = {pd_num : $(this).data("num"), ct_amount : $(this).attr("data-amount")};
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
