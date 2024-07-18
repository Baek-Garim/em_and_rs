    $(document).ready(function() {
      function validateForm() {
        var userPw = $('#user_pw').val();
        var password2 = $('#password2').val();
        var userName = $('#user_name').val();
        var phone = $('#phone').val();

        var pwCheck = userPw.length >= 6 && userPw.length <= 16 && /[a-zA-Z]/.test(userPw) && /\d/.test(userPw);
        var pw2Check = userPw === password2;
        var nameCheck = userName.length >= 2 && userName.length <= 6;
        var phoneCheck = /^\d{10,11}$/.test(phone);

        $('#pwchk').toggle(!pwCheck);
        $('#pw2chk').toggle(!pw2Check);
        $('#namechk').toggle(!nameCheck);
        $('#phonechk').toggle(!phoneCheck);

        return pwCheck && pw2Check && nameCheck && phoneCheck;
      }

      $('input').on('input', function() {
        var isValid = validateForm();
        $('#s-submit-btn').prop('disabled', !isValid);
      });
    });