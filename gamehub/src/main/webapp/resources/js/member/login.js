
$(document).ready(function(){
	$('#searchIdBtn').click(function(){
	   	$('section').load("/html/member/searchId.html");
	});
	$('#searchPwBtn').click(function(){
	   	$('section').load("/html/member/searchPw.html");
	});
	//공백, 한글을 못사용하게 한다.
	$('#mem_id').on("blur keyup", function() {
		$(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ) );
		$(this).val( $(this).val().replace( /\s/g,'' ) );
	});
	//공백, 한글을 못사용하게 한다.
	$('#mem_passwd').on("blur keyup", function() {
		$(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ) );
		$(this).val( $(this).val().replace( /\s/g,'' ) );
	});
});
