var stat = true;
var checkId_stat = true; 
var checkPw_stat = true; 
var checkNick_stat = true; 

$(document).ready(function(){
	//공백, 한글을 못사용하게 한다.
	$('#mem_id').on("blur keyup", function() {
		$(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ) );
		$(this).val( $(this).val().replace( /\s/g,'' ) );
	});
	$('#mem_passwd').on("blur keyup", function() {
		$(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ) );
		$(this).val( $(this).val().replace( /\s/g,'' ) );
	});
	$('#passwd_cf').on("blur keyup", function() {
		$(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ) );
		$(this).val( $(this).val().replace( /\s/g,'' ) );
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
		$(this).val( $(this).val().replace( /[^0-9]/g,'' ) );
	
		if($(this).val().length > 2){
			$('#mem_tel2').focus();
		}
	});
	$('#mem_tel2').keydown(function(event){
		$(this).val( $(this).val().replace( /[^0-9]/g,'' ) );
		
		if($(this).val().length > 3){
			$('#mem_tel3').focus();
		}
	});
	$('#mem_tel3').keydown(function(event){
		$(this).val( $(this).val().replace( /[^0-9]/g,'' ) );
		
		if($(this).val().length > 3){
			$('#mem_nick').focus();
		}
	});
	$('#mem_addr_num').keydown(function(){
		$(this).val( $(this).val().replace( /[^0-9]/g,'' ) );
	});
	//input 이름
	$("#mem_name").focus(function(){
		$("#name-help").slideDown(500);
	}).blur(function(){
		$("#name-help").slideUp(500);
		this.value=this.value.replace(/\s/g,'');
	});//END OF 이름
	
	//input 상세주소
	$("#mem_addr_detail").focus(function(){
		$("#addr_detail-help").slideDown(500);
	}).blur(function(){
		$("#addr_detail-help").slideUp(500);
	});//END OF 상세주소

	//input 아이디, 중복체크까지 동시에
	$("#mem_id").focus(function(){
		$("#id-help").slideDown(500);
	}).blur(function(){
		$("#id-help").slideUp(500);

		//아이디 입력란에 입력값이 있을 경우
		if($.trim($("#mem_id").val())){
			checkId_stat = true;
			var jsonv = {mem_id : $("#mem_id").val()};
			$.ajax({
				type:"POST", 
				url:"/member/idCheck",
				data:jsonv, 
				success:function(data){
					if(data == "Y"){
						$("#idcheck").text("회원가입이 가능합니다.");
						$("#idcheck").css("color","#29d616");
					}else if(data == "N"){
						$("#idcheck").text("이미 가입한 ID입니다.");
						$("#idcheck").css("color","red");
						$("#mem_id").val("");
						$("#mem_id").focus();
						check_stat = false;
					}
				}//END OF 콜백함수 즉 DB상 중복체크를 해온결과가 data
			});//END OF AJAX
		}//END OF IF 아이디 입력이 있는경우
		else{
			$("#idcheck").text("아이디를 입력해주세요.");
			$("#idcheck").css("color","red");
			$("#mem_id").focus();
			check_stat = false;
		}//END OF ID값이 없는 경우
	});//END OF ID
	
	//input 닉네임, 중복체크까지 동시에
	$("#mem_nick").focus(function(){
		$("#nick-help").slideDown(500);
	}).blur(function(){
		$("#nick-help").slideUp(500);
		this.value=this.value.replace(/\s/g,'');
		//닉네임 입력란에 입력값이 있을 경우
		if($.trim($("#mem_nick").val())){
			checkNick_stat = true;
			var jsonv = {mem_nick : $("#mem_nick").val()};
			$.ajax({
				type:"POST", 
				url:"/member/nickCheck",
				data:jsonv, 
				success:function(data){
					if(data == "Y"){
						$("#nickcheck").text("회원가입이 가능합니다.");
						$("#nickcheck").css("color","#29d616");
					}else if(data == "N"){
						$("#nickcheck").text("이미 가입한 별명입니다.");
						$("#nickcheck").css("color","red");
						$("#mem_nick").val("");
						$("#mem_nick").focus();
						checkNick_stat = false;
					}
				}//END OF 콜백함수 즉 DB상 중복체크를 해온결과가 data
			});//END OF AJAX
		}//END OF IF Nick 입력이 있는경우
		else{
			$("#nickcheck").text("닉네임을 입력해주세요.");
			$("#nickcheck").css("color","red");
			$("#mem_nick").focus();
			checkNick_stat = false;
		}//END OF Nick값이 없는 경우
	});//END OF Nick
	
	//input 암호
	$("#mem_passwd").focus(function(){
		$("#passwd-help").slideDown(500);
	}).blur(function(){
		$("#passwd-help").slideUp(500);
	});//END OF passwd
	
	//input 전화번호
	$("#mem_tel1").focus(function(){
		$("#mem_tel-help").slideDown(500);
	});
	$("#mem_tel3").blur(function(){
		$("#mem_tel-help").slideUp(500);
	});//END OF 전화번호
	
	//input 암호 재입력에 일치 검사
	$("#passwd_cf").focus(function(){
		$("#passwd_cf-help").slideDown(500);
	}).blur(function(){
		$("#passwd_cf-help").slideUp(500);
		
		//암호 입력란에 입력값이 있을 경우
		if($.trim($("#passwd_cf").val())){
			if($("#passwd_cf").val() == $("#mem_passwd").val()){
				$("#pwcheck").text("암호가 일치합니다.");
				$("#pwcheck").css("color","#29d616");
				checkPw_stat = true;
			}else{
				$("#pwcheck").text("암호가 일치하지 않습니다.");
				$("#pwcheck").css("color","red");
				$("#passwd_cf").val("");
				checkPw_stat = false;
			}
		}else checkPw_stat = false;
	});//END OF passwd 확인
	
	//우편번호(다음주소)
	$("#mem_addr_num").focus(function(){
		daumPostcode();
	});
	//다음주소내에 접기 버튼
	$('#btnFoldWrap').click(function(){
		$('#wrap').css('display', 'none');
	});
	
	$(".rgform").submit(function(){
		checkIt();
		if(stat && checkId_stat && checkPw_stat && checkNick_stat){
			if($('#receive_check').is(':checked')){
				$("#mem_receive").val("Y");
			}else{
				$("#mem_receive").val("N");
			}
			$("#mem_tel").val($("#mem_tel1").val()+"-"+$("#mem_tel2").val()+"-"+$("#mem_tel3").val());
			return true;
		}else return false;		
	});
	
});

//우편번호 검색에 쓰일 div태그 오브젝트
var element_wrap = document.getElementById('wrap');
//우편번호 검색함수
function daumPostcode(){
	// 현재 scroll 위치를 저장해놓는다.
    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
    	oncomplete: function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = data.address; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 기본 주소가 도로명 타입일때 조합한다.
            if(data.addressType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $('#mem_addr_num').val(data.zonecode);//5자리 새우편번호 사용
            $('#mem_addr_basic').val(fullAddr);

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            $('#wrap').css('display', 'none');

            // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
            document.body.scrollTop = currentScroll;
        },
        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
        onresize : function(size) {
        	$('#wrap').css('height', "size.height+'px'");
        },
        width : '100%',
        height : '100%'
    }).embed(element_wrap);
    
    $('#wrap').css('display', 'block');
}

function checkIt(){
	stat = true;
	
	if($("#mem_passwd").val().length<6){
		alert("암호가 6글자 미만입니다. 다시입력하세요.");
		$("#mem_passwd").val("");
		$("#passwd_cf").val("");
		$("#mem_passwd").focus();
		stat = false;
		return false;
	}
	if($("#mem_nick").val().length<2){
		alert("별명이 한글자 입니다. 다시입력하세요.");
		$("#mem_nick").val("");
		$("#mem_nick").focus();
		stat = false;
		return false;
	}
	if($("#mem_tel1").val().length<3 || $("#mem_tel2").val().length<3 || $("#mem_tel3").val().length<3){
		alert("유효하지 않은 연락처 입니다. 숫자를 3글자 이상 입력하세요.");
		$("#mem_tel1").val("");
		$("#mem_tel2").val("");
		$("#mem_tel3").val("");
		$("#mem_tel1").focus();
		stat = false;
		return false;
	}	
}