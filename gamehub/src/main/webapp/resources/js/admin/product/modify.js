$(document).ready(function(){
	var price = $("#pd_price").val();
	var discount = $("#pd_discount").val();
	var result = (price/100)*(100-discount);
	$("#resultprice").text("최종가격 : " + result + "원");
	
	if($('#pd_bonus_check').is(':checked')){
		$('#bonus-help').slideDown(500);
	}
	
	
});
