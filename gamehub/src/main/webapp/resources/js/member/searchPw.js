var searchStat = true;

$(document).ready(function(){
	//공백, 한글을 못사용하게 한다.
	$('#mem_id').on("blur keyup", function() {
		$(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ) );
		$(this).val( $(this).val().replace( /\s/g,'' ) );
	});
	//공백을 못사용하게 한다.
	$('#mem_name').blur(function(){
		this.value=this.value.replace(/\s/g,'');
	});
	//숫자만 입력가능하게
	$('#mem_tel1').focus(function() {
		$('#mem_tel1').val("");
	});
	$('#mem_tel2').focus(function() {
		$('#mem_tel2').val("");
	});
	$('#mem_tel3').focus(function() {
		$('#mem_tel3').val("");
	});
	$('#mem_tel1').keydown(function(){
		this.value=this.value.replace(/[^0-9]/g,'');
		if(this.value.length > 2){
			$('#mem_tel2').focus();
		}
	});
	$('#mem_tel2').keydown(function(){
		this.value=this.value.replace(/[^0-9]/g,'');
		if(this.value.length > 3){
			$('#mem_tel3').focus();
		}
	});
	$('#mem_tel3').keydown(function(){
		this.value=this.value.replace(/[^0-9]/g,'');
	});
	$('#searchBtn').click(function(){
		searchCheckIt();
		$("#mem_tel").val($("#mem_tel1").val()+"-"+$("#mem_tel2").val()+"-"+$("#mem_tel3").val());
		if(searchStat){
			var jsonv = {mem_id : $("#mem_id").val(),mem_name : $("#mem_name").val(), 
					mem_tel : $("#mem_tel").val()};
			
			$.ajax({
				type:"POST", 
				url:"/member/searchPw",
				data:jsonv, 
				success:function(data){
					if(data == "searchPw"){
						alert("이메일로 임시비밀번호를 발송했습니다.");
						window.location.assign('/member/login');
					}else{
						alert("일치하는 계정이 없습니다.");
						$('#mem_id').focus();
					}					
				}//END OF 콜백함수 즉 DB상 id를 찾아온 결과 data
			});//END OF AJAX
		}
	});
});

function searchCheckIt(){
	searchStat = true;
	if($("#mem_id").val() == ""){
		alert("ID를 입력하지 않았습니다.");
		$("#mem_id").focus();
		searchStat = false;
		return false;
	}
	if($("#mem_name").val() == ""){
		alert("이름을 입력하지 않았습니다.");
		$("#mem_name").focus();
		searchStat = false;
		return false;
	}
	if($("#mem_tel1").val().length<3 || $("#mem_tel2").val().length<3 || $("#mem_tel3").val().length<3){
		alert("유효하지 않은 연락처 입니다. 숫자를 3글자 이상 입력하세요.");
		$("#mem_tel1").val("");
		$("#mem_tel2").val("");
		$("#mem_tel3").val("");
		$("#mem_tel1").focus();
		searchStat = false;
		return false;
	}	
}