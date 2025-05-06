function deliverySave() {
    deliveryValidInit();
    if (!deliveryValidation()) {
        return;
    }
    deliveryInfoTrans();
}

function deliveryValidation() {
    let temp = $("#receiveName").val();
    if (!checkLength(temp) || !onlyKo(temp)) {
        $("#receiveName").after("<div>이름을 입력해주세요.</div>");
        $("#receiveName").next().css("color", "red");
        $("#receiveName").focus();
        return false;
    }

    temp = $("#receivePhone").val();
    if (!checkLength(temp) || !onlyPhone(temp)) {
        $("#receivePhone").after("<div>전화번호를 입력해주세요.</div>");
        $("#receivePhone").next().css("color", "red");
        $("#receivePhone").focus();
        return false;
    }

    temp = $("#receiveAddr").val();
    if (!checkLength(temp)) {
        $("#receiveAddr").after("<div>주소를 입력해주세요.</div>");
        $("#receiveAddr").next().css("color", "red");
        $("#receiveAddr").focus();
        return false;
    }
    return true;
}

function deliveryValidInit() {
    $("#receiveName").next().remove();
    $("#receivePhone").next().remove();
    $("#receiveAddr").next().remove();
}

function deliveryInfoTrans() {
    let check = 0;
    let url;
    if ($("#receiveDefault").is(":checked")) {
        check = 1;
    }

    if ($("#savaBtn").val() == "") {
        url = "/peter/delivery/DeliveryPeterProc";
    } else {
        url = "/peter/delivery/DeliveryPeterTransProc";
    }

    fetch(url, {    // payList fragment만 반환하는 컨트롤러
        method: 'POST',  // POST 요청
        body: new URLSearchParams({  // POST 데이터 설정
            'deliRecvName': $("#receiveName").val(),
            'deliRecvPhone': $("#receivePhone").val(),
            'deliLatitude': parseFloat($("#receiveLatitude").val()),
            'deliLongtitude': parseFloat($("#receiveLongitude").val()),
            'deliRoadAddr': $("#receiveAddr").val(),
            'deliDetailAddr': $("#receiveDetailAddr").val(),
            'deliText': $("#receiveText").val(),
            'deliMain': check,
            'userSeq': sessionSeq,  // 예시로 session 변수 사용
            'deliSeq': $("#savaBtn").val()  // 예시로 session 변수 사용
        })
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById('address').innerHTML = "";
        document.getElementById('address').innerHTML = html;
    });
}

function deliveryModify(e) {
    $("#shipping").slideUp();
    $("#shipping").slideDown();
    $("#savaBtn").val($(e).data('value'));
    
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/peter/delivery/DeliveryPeterModifyProc"
        ,data: { "deliSeq" : $(e).data('value') }
        ,success: function(response) {
            $("#receiveLatitude").val(response.dto.deliLatitude);
            $("#receiveLongitude").val(response.dto.deliLongtitude);
            $("#receiveName").val(response.dto.deliRecvName);
            $("#receivePhone").val(response.dto.deliRecvPhone);
            $("#receiveAddr").val(response.dto.deliRoadAddr);
            $("#receiveDetailAddr").val(response.dto.deliDetailAddr);
            $("#receiveText").val(response.dto.deliText);
            if (response.dto.deliMain == 1) {
                $("#receiveDefault").attr("checked", true);
            } else {
                $("#receiveDefault").attr("checked", false);
            }
        }
        ,error : function(jqXHR) {
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

function initForm() {
    $("#receiveLatitude").val("");
    $("#receiveLongitude").val("");
    $("#receiveName").val("");
    $("#receivePhone").val("");
    $("#receiveAddr").val("");
    $("#receiveDetailAddr").val("");
    $("#receiveText").val("");
    $("#receiveDefault").attr("checked", false);
    $("#savaBtn").val("");
}

function addDelivery() {
    $("#shipping").slideUp();
    $("#shipping").slideDown();
    initForm();
}

function deliveryDelete(e) {
    if (!mainCheck(e)) {
        return;
    }

    fetch('/peter/delivery/DeliveryPeterDeltProc', {    // payList fragment만 반환하는 컨트롤러
        method: 'POST',  // POST 요청
        body: new URLSearchParams({  // POST 데이터 설정
            'userSeq': sessionSeq,  // 예시로 session 변수 사용
            'deliSeq': $(e).data('value')  // 예시로 session 변수 사용
        })
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById('address').innerHTML = "";
        document.getElementById('address').innerHTML = html;
    });
}

function mainCheck(e) {
    if ($(e).prev().prev().html() === "기본 배송지") {
        modalTitle.innerText = "알림";
        modalText.innerText = "기본 배송지를 변경해주세요.";
        modalCloseButton.style.display = "block";
        $(".bg-body-tertiary").css("backgroundColor", "#E4EEF7");
        $(".modal-body").css("backgroundColor", "#EBF5FF");
        $(".modal-footer").css("backgroundColor", "#EBF5FF");
        modal.style.display = "block";
        return false;
    }
    return true;
}

function selectDelivery(e) {
    fetch('/peter/delivery/DeliveryPeterPayProc', {    // payList fragment만 반환하는 컨트롤러
        method: 'POST',  // POST 요청
        body: new URLSearchParams({  // POST 데이터 설정
            'deliSeq': $(e).val()  // 예시로 session 변수 사용
        })
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById('receiveInfo').innerHTML = "";
        document.getElementById('receiveInfo').innerHTML = html;
    });
    $('#select-destination').modal('hide');
}