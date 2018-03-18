$(document).ready(function(){
	
	$('#agreecb3').click(function(){
		if($('#agreecb3').is(':checked')){
			$('.agreecb').each(function(){
				this.checked = true;
			});			
		}else{
			$('.agreecb').each(function(){
				this.checked = false;
			});	
		}
	});
	
	$('#joinbtn').click(function(){
		if($('#agreecb1').is(':checked') && $('#agreecb2').is(':checked')){
			window.location.assign('/member/register');
		}else{
			alert("약관에 동의하지 않을시 가입할 수 없습니다.");
		}
	});//동의 후 계속 버튼 클릭이벤트----------------------------->	
	
});//html문서 로드 완료시 작동 함수

