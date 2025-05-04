function deliverySave() {
    deliveryValidInit();
    deliveryValidation();
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