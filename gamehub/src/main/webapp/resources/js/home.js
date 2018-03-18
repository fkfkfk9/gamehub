$(document).ready(function(){
	
	$('#topJoin').click(function(){
		window.location.assign('/member/agreePage');
	});//회원가입 버튼 클릭이벤트----------------------------->
	
	$('#topLogin').click(function(){
		window.location.assign('/member/login');
	});//로그인 버튼 클릭이벤트----------------------------->
	
	$('#topLogout').click(function(){
		window.location.assign('/member/logout');
	});//로그인 버튼 클릭이벤트----------------------------->
	
	$('#topMypage').click(function(){
		window.location.assign('/member/mypage');
	});//로그인 버튼 클릭이벤트----------------------------->
	
	$('#topAdminpage').click(function(){
		window.location.assign('/admin/home');
	});//로그인 버튼 클릭이벤트----------------------------->
	
	$('#topCart').click(function(){
		window.location.assign('/cart/list');
	});//로그인 버튼 클릭이벤트----------------------------->
	
});//html문서 로드 완료시 작동 함수

