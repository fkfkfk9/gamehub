$(document).ready(function(){
	//페이지 버튼
	$(".pagination li a").on("click", function(event){
		
		event.preventDefault(); 
		
		var targetPage = $(this).attr("href");
		
		var pageingForm = $("#PagingForm");
		pageingForm.find("[name='pageNum']").val(targetPage);
		pageingForm.attr("action","/admin/product/list").attr("method", "get");
		pageingForm.submit();
	});
	$("#submitBtn").click(function() {
		var str = "";
		if($('#cateCode').val()){
			if($('#cg_code').val() == 0 && $('#cg_parent').val() == 0 && $('#cg_ancestor').val() == 0) str = "ctg1_";
			else if($('#cg_ancestor').val() == 0) str = "ctg2_";
			else if($('#cg_code').val() != 0 && $('#cg_parent').val() != 0 && $('#cg_ancestor').val() != 0) str = "ctg3_";
		}
		if($('#keyword').val()){
			var key = $('#keyword').val();
			var eckey = encodeURIComponent(key);
			$('#keyword').val(eckey);
			str += "pn_";		
		}
		if($('#minPrice').val() && !$('#maxPrice').val()){
			$('#maxPrice').val("0");
			str += "ppmin_";
		}
		else if(!$('#minPrice').val() && $('#maxPrice').val()){
			$('#minPrice').val("0");
			str += "ppmax_";
		}
		else if($('#minPrice').val() && $('#maxPrice').val()) str += "pp_";
		else{
			$('#maxPrice').val("0");
			$('#minPrice').val("0");
		}
		if($('#date').val())str += "pd_";
		else $('#date').val("0");
		if($('#display').val())str += "pb_";
		$('#searchType').val(str.slice(0,-1));
		//$('#searchForm').attr('method','');
		$('#searchForm').submit();
	});
	$("#day").click(function() {
		$('#date').val('1');
		$('#dateInfo').text($("#day").val());
	});
	$("#week").click(function() {
		$('#date').val('7');
		$('#dateInfo').text($("#week").val());
	});
	$("#month").click(function() {
		$('#date').val('30');
		$('#dateInfo').text($("#month").val());
	});
	$("#every").click(function() {
		$('#date').val("");
		$('#dateInfo').text("");
	});
	//판매여부 승인 버튼
	$('#okBtn').click(function() {
		var str = "";
		$('.buycheck').each(function() {
			if($(this).is(':checked')){
				str += $(this).data('num') + ",";				
			}
		});
		$('#pd_num').val(str.slice(0,-1));
		$('#pd_buy').val('Y');
		$.ajax({
		   url:"/admin/product/approval",
		   type:"post",
		   data: {pd_num:$('#pd_num').val(), pd_buy:$('#pd_buy').val()},
		   dataType:"text",
		   success:function(result){
			   if(result == 'updated'){
				   $('#PagingForm').submit();
			   }
		   }
		 });
	});
	//판매여부 취소버튼
	$('#noBtn').click(function() {
		var str = "";
		$('.buycheck').each(function() {
			if($(this).is(':checked')){
				str += $(this).data('num') + ",";				
			}
		});
		$('#pd_num').val(str.slice(0,-1));
		$('#pd_buy').val('N');
		$.ajax({
		   url:"/admin/product/approval",
		   type:"post",
		   data: {pd_num:$('#pd_num').val(), pd_buy:$('#pd_buy').val()},
		   dataType:"text",
		   success:function(result){
			   if(result == 'updated'){
				   $('#PagingForm').submit();
			   }
		   }
		 });
	});
});
