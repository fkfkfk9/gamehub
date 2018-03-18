$(document).ready(function(){
	
	$('#topadmin').click(function(){
		window.location.assign('/admin/menager');
	});//회원가입 버튼 클릭이벤트----------------------------->
	
	$('#topHomepage').click(function(){
		window.location.assign('/');
	});//로그인 버튼 클릭이벤트----------------------------->
	
	$('#topLogout').click(function(){
		window.location.assign('/member/logout');
	});//로그인 버튼 클릭이벤트----------------------------->
		
});//html문서 로드 완료시 작동 함수

