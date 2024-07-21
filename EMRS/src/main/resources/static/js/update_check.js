$(document).ready(function() {

	let patternEmail = /^[a-z0-9_-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
	let namePattern = /([^가-힣a-zA-Z])/i;
	let phonePattern = /^\d{3}-\d{4}-\d{4}$/;

	let a = [0,0,0,0,0];

	$("#email").on("propertychange change keyup paste input", function(){
		if(!patternEmail.test($.trim($("#email").val()))){
			$('#emailchk').html("아이디는 이메일 형식으로 입력해 주세요.").css('color','red');
			a[0] = 0;
		} else {
			var email = $('#email').val();
			$.ajax({
				url: '/emailCheck',
				method: 'post',
				data : { "email": email },
				dataType : 'text',
				success : function(data) {
					if (data == "redundancy") {
						$('#emailchk').html("중복된 아이디입니다").css('color', 'red');
						a[0] = 0;
					} else if (data == "noredundancy") {
						$('#emailchk').html("사용가능한 아이디입니다").css('color', 'green');
						a[0] = 1;
					} else {
						$('#emailchk').html("아이디를 입력해주세요").css('color', 'red');
						a[0] = 0;
					}
				},
				error: function (data, status, err) {
					// 에러날때 확인하는 곳
				},
			});
		}
		isSubmit();
	});
	
	// 비밀번호
	$("#user_pw").on("propertychange change keyup paste input", function(){
		let pw = $('#user_pw').val();
		let num = pw.search(/[0-9]/g);
		let engLow = pw.search(/[a-z]/ig);

		// 빈값		
		if(!$('#user_pw').val()){
			$('#pwchk').html("영문과 숫자를 포함한 6~16자").css('color','red');		
			a[1] = 0;
		// 자릿수 부족
		} else if($('#user_pw').val().length < 6 || $('#user_pw').val().length > 16){
			$('#pwchk').html("영문과 숫자를 포함한 6~16자").css('color','red');		
			a[1] = 0;
		} else {
			// 조건 부족
			if(num < 0 || engLow < 0 ){
				$('#pwchk').html("영문과 숫자를 포함한 6~16자").css('color','red');	
				a[1] = 0;
			} else {
				// 성공
				$('#pwchk').html("사용가능한 비밀번호 입니다").css('color', 'green');
				a[1] = 1;
			}
		}
		pw2chk();
		isSubmit();
	});
	
	// 비밀번호 확인
	$("#password2").on("propertychange change keyup paste input", function(){
		pw2chk();
	});

	function pw2chk(){
		let pw = $('#user_pw').val();
		// 빈값		
		if(!$('#password2').val()){
			$('#pw2chk').html("비밀번호가 일치하지 않습니다.").css('color','red');		
			a[2] = 0;
		} else {
			// 조건 부족
			if(pw != $('#password2').val()){
				$('#pw2chk').html("비밀번호가 일치하지 않습니다.").css('color','red');		
				a[2] = 0;
			} else {
				// 성공
				$('#pw2chk').html("비밀번호가 일치 합니다.").css('color', 'green');
				a[2] = 1;
			}
		}
		isSubmit();
	}
	
	// 이름 확인
	$("#user_name").on("propertychange change keyup paste input", function(){
		var user_name = $('#user_name').val();
		// 빈값		
		if(!user_name){
			$('#namechk').html("이름을 입력해 주세요.").css('color','red');		
			a[3] = 0;
		}else if(namePattern.test($.trim($("#user_name").val()))){
			$('#namechk').html("정확한 값을 입력하세요.").css('color','red');					
			a[3] = 0;
		} else {
			// 조건 부족
			if(user_name.length < 2 || user_name.length > 6){
				$('#namechk').html("이름을 입력해 주세요.").css('color','red');		
				a[3] = 0;
			} else {
				// 성공
				$('#namechk').html("").css('color', 'green');
				a[3] = 1;
			}
		}
		isSubmit();
	});	
	
	// 전화번호 확인 및 입력 형식 변경
	$(document).on("propertychange change keyup paste input", "#phone", function(){
		$(this).val( $(this).val()
			.replace(/[^0-9]/g, "")
			.replace(/(\d{3})(\d{4})(\d{4})$/, "$1-$2-$3")
			.replace("--", "-"));

		if(!phonePattern.test($.trim($("#phone").val()))){
			$('#phonechk').html("하이픈(-)을 제외한 전화번호를 입력해 주세요.").css('color','red');
			a[4] = 0;
		} else {
			var phone = $('#phone').val();
			$.ajax({
				url: '/phoneCheck',
				method: 'post',
				data : { "phone": phone },
				dataType : 'text',
				success : function(data) {
					if (data == "redundancy") {
						$('#phonechk').html("중복된 핸드폰 번호입니다").css('color', 'red');
						a[4] = 0;
					} else if (data == "noredundancy") {
						$('#phonechk').html("사용가능한 번호 입니다").css('color', 'green');
						a[4] = 1;
					}
				},
			});
		}
		isSubmit();
	});

	// 유효성 검사를 모두 통과하면 해당 함수 실행할 것
	function isSubmit(){
		let ok = false;

		// 비밀번호가 채워져 있으면 비밀번호 확인도 채워져 있는지 확인
		if (a[1] == 1 && a[2] == 0) {
			ok = false;
		} else {
			// 특정 필드 중 하나만 채워져 있으면 ok를 true로 설정
			for(let i = 1; i <= 4; i++) {
				if (a[i] == 1) {
					ok = true;
					break;
				}
			}
		}
		
		if(ok){
			$('#s-submit-btn').attr("disabled", false);
		} else {
			$('#s-submit-btn').attr("disabled", true);			
		}
	}	
});
