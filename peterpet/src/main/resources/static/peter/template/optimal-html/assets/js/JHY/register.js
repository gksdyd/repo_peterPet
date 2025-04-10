/* ===============================================-->
                    등록 관련 JS
   ===============================================--> */
  
// 등록 및 수정하는 함수
document.getElementById("userUpdateBtn").onclick = function() {
    styleInit();
    validationInit();

    if (!validation()) {
        return false;
    }

    userform.action = goUrlPeterUserUpdt;
    userform.method = "post";
    userform.submit();
}

document.getElementById("petSubmitBtn").onclick = function() {
    petStyleInit();
    petValidationInit();

    if (!petValidation()) {
        return false;
    }

    let array = [];
    for (let i = 0; i < document.querySelectorAll("#createPersonal > .removeFunc").length; i++) {
        array.push(document.querySelectorAll("#createPersonal > .removeFunc")[i].value);
    }
    petPersonalArray.value = array;

    array = [];
    for (let i = 0; i < document.querySelectorAll("#createDisease > .removeFunc").length; i++) {
        array.push(document.querySelectorAll("#createDisease > .removeFunc")[i].value);
    }
    petDiseaseArray.value = array;

    if ($(this).val() == REGISTER) {
        petform.action = goUrlPeterPetInst;
    } else if ($(this).val() == MODIFY) {
        petform.action = goUrlPeterPetUpdt;
    }
    petform.method = "post";
    petform.submit();
}

$(".selectPet ul li").on("click", function(e) {
    var el = $(e.target).closest('li');
    // 다른 active되어있는 li의 class를 제거
    el.siblings('li').removeClass("selectActive");
    el.addClass("selectActive");
});

let temp = 0;
$("#selectBtn").on("click", function() {
    if ($(this).val() == VALUE_BUTTON_VARIETY) {
        $("#petVariety").val($(".selectActive a").text());
        $("#petVarieties").val($(".selectActive a").attr("value"));
        $("#modalPet").modal('hide');
    } else if ($(this).val() == VALUE_BUTTON_PERSONAL) {
        if (temp == $(".selectActive a").attr("value")) {
            $("#modalPet").modal('hide');
            return false;
        }
        temp = $(".selectActive a").attr("value");
        let personalBadge = $("<span></span>").addClass("badge").addClass("rounded-pill").addClass("badgeStyle");
        let personalCloseBtn = $("<button></button>").addClass("btn-close").addClass("removeFunc").css('font-size', 'xx-small').prop("type", "button");
        $("#createPersonal").append(personalBadge.text($(".selectActive a").text()));
        $("#createPersonal").append(personalCloseBtn.attr('value', $(".selectActive a").attr("value")));
        $(".selectActive").css('border-bottom', '');
        $(".selectActive a").hide();
        $("#modalPet").modal('hide');
    }  else if ($(this).val() == VALUE_BUTTON_DISEASE) {
        if (temp == $(".selectActive a").attr("value")) {
            $("#modalPet").modal('hide');
            return false;
        }
        temp = $(".selectActive a").attr("value");
        let diseaseBadge = $("<span></span>").addClass("badge").addClass("rounded-pill").addClass("badgeStyle");
        let diseaseCloseBtn = $("<button></button>").addClass("btn-close").addClass("removeFunc").css('font-size', 'xx-small').prop("type", "button");
        $("#createDisease").append(diseaseBadge.text($(".selectActive a").text()));
        $("#createDisease").append(diseaseCloseBtn.attr('value', $(".selectActive a").attr("value")));
        $(".selectActive").css('border-bottom', '');
        $(".selectActive a").hide();
        $("#modalPet").modal('hide');
    }
});

$(".search-btn").on("click", function() {
    let id = $(this).attr("id");
    
    $(".selDogVarieties").hide();
    $(".selCatVarieties").hide();
    $(".selPersonal").hide();
    $(".selDisease").hide();

    if (id == ID_SEARCH_VARIETY) {
        $("#modalPetLabel").text("품종 선택");
        $("#selectBtn").val(VALUE_BUTTON_VARIETY);
        if (dog.checked) {
            $(".selDogVarieties").show();
        } else if (cat.checked) {
            $(".selCatVarieties").show();
        }
    } else if (id == ID_SEARCH_PERSONAL) {
        $(".selPersonal").show();
        $("#modalPetLabel").text("성격/특징 선택");
        $("#selectBtn").val(VALUE_BUTTON_PERSONAL);
    } else if (id == ID_SEARCH_DISEASE) {
        $(".selDisease").show();
        $("#modalPetLabel").text("질병/질환 선택");
        $("#selectBtn").val(VALUE_BUTTON_DISEASE);
    }
})

$(document).on('click', '.removeFunc', function(){
    if (temp == $(this).val()) {
        temp = 0;
    }

    if ($(this).parent().attr("id") == "createPersonal") {
        for (let j = 0; j < document.querySelectorAll("li.selPersonal").length; j++) {
          if (document.querySelectorAll("li.selPersonal > a")[j].getAttribute("value") == $(this).val()) {
            document.querySelectorAll("li.selPersonal")[j].style = 'border-bottom : 1px solid gray';
            document.querySelectorAll("li.selPersonal > a")[j].style.display = 'block';
            break;
          }
        }
    } else if ($(this).parent().attr("id") == "createDisease") {
        for (let j = 0; j < document.querySelectorAll("li.selDisease").length; j++) {
          if (document.querySelectorAll("li.selDisease > a")[j].getAttribute("value") == $(this).val()) {
            document.querySelectorAll("li.selDisease")[j].style = 'border-bottom : 1px solid gray';
            document.querySelectorAll("li.selDisease > a")[j].style.display = 'block';
            break;
          }
        }
    }

    for (let i = 0; i < document.querySelectorAll(".removeFunc").length; i++) {
        if (document.querySelectorAll(".removeFunc")[i].value == $(this).val()) {
        document.querySelectorAll(".removeFunc")[i].remove();
        document.querySelectorAll(".badgeStyle")[i].remove();
        break;
      }
    }
});

document.getElementById("changeBtn").onclick = function() {
    passwordStyleInit();
    passwordValidationInit();

    if (!passwordValidation()) {
        return false;
    }
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: URL_PASSWORD_CHANGE_PETER
        ,data: { "userPassword" : userPassword.value, "newPassword" : newPassword.value, "userSeq" : $("#userSeq").val() }
        ,success: function(response) {
            if (response.rt == "success") {
                location.href = URL_INDEX_VIEW_PETER;
            } else {
                $(".bg-body-tertiary").css("backgroundColor", "#E4EEF7");
                $(".modal-body").css("backgroundColor", "#EBF5FF");
                $(".modal-footer").css("backgroundColor", "#EBF5FF");
                modalTitle.innerText = "알림";
                modalText.innerText = "비밀번호가 일치하지 않습니다!";
                modalCloseButton.style.display = "block";
                modal.style.display = "block";
            }
        }
        ,error : function(jqXHR){
          alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}