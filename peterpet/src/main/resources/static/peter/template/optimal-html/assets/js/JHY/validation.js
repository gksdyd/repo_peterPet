/* ===============================================-->
                    validation 관련 Js
   ===============================================--> */

// 모든 공백 제거
allGapRemove = function(text) {
    return text.replace(/ /g, "");
}

// 양 옆 공백 제거
bothSidesGapRemove = function(text) {
    return text.trim();
}

// 길이 체크
checkLength = function(text) {
    if (text.length == 0) {
        return false;
    }
    return true;
}

// 길이 사이즈
checkLengthCnt = function(text) {
    return text.length;
}

// 영어, 숫자만
onlyKoEngNumValid = function(text) {
    if (!regex1.test(text)) {
        return false;
    }
    return true;
}

// 영어, 한글, 숫자, ~, /, (, ) 만
onlyEngKoNumSpecialChar = function(text) {
    if (!regex2.test(text)) {
        return false;
    }
    return true;
}

// 영어, 숫자, ~, /, (, ) 만
onlyEngNumSpecialChar = function(text) {
    if (!regex3.test(text)) {
        return false;
    }
    return true;
}

// 숫자만
onlyNum = function(text) {
    if (!regex4.test(text)) {
        return false;
    }
    return true;
}

// 영어, 한글, 숫자, 특수문자만
onlyEngKoNumAllSpecialChar = function(text) {
    if (!regex5.test(text)) {
        return false;
    }
    return true;
}

// 한글만
onlyKo = function(text) {
    if (!regex6.test(text)) {
        return false;
    }
    return true;
}

// 핸드폰
autoPhoneNum = function(target){
    target.value = target.value
    .replace(/[^0-9]/g, "")
    .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
}

onlyPhone = function(text) {
    if (!regexPhone.test(text)) {
        return false;
    }
    return true;
}

// 이메일
onlyEmail = function(text) {
    if (!regexEmail.test(text)) {
        return false;
    }
    return true;
}

// 무게만
onlyWeight = function(text) {
    if (!regexWeight.test(text)) {
        return false;
    }
    return true;
}

idDuplicateCheck = function() {
    let result = false;

    $.ajax({
        async: false 
        ,cache: false
        ,type: "post"
        ,url: URL_ID_CHECK_PETER
        ,data: { "userId" : id.value }
        ,success: function(response) {
          if(response.rt == "success") {
            result = true;
          } else {
            idValid.innerText = "중복된 아이디입니다.";
            idValid.style.display = "block";
            id.style.borderColor = "red";
            id.focus();
          }
        }
        ,error : function(jqXHR){
          alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
    return result;
}

emailDuplicateCheck = function() {
    let result = false;

    $.ajax({
        async: false 
        ,cache: false
        ,type: "post"
        ,url: URL_EMAIL_CHECK_PETER
        ,data: { "userEmail" : email.value }
        ,success: function(response) {
          if(response.rt == "success") {
            result = true;
          } else {
            emailValid.innerText = "중복된 이메일입니다.";
            emailValid.style.display = "block";
            email.style.borderColor = "red";
            email.focus();
          }
        }
        ,error : function(jqXHR){
          alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
    return result;
}