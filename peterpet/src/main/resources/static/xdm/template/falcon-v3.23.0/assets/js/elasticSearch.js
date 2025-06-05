var mode = 0;

function indexSearch() {
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/elastic/xdm/ElasticXdmIndexSearch"
        ,data: {  }
        ,success: function(response) {
            $(".card-body").html(response);
            mode = 1;
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

function docSearch() {
    if ($("#shIndex").val() === "") {
        $(".card-body").html("");
        mode = 0;
        return;
    }

    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/elastic/xdm/ElasticXdmDocSearch"
        ,data: { "index" : $("#shIndex").val() }
        ,success: function(response) {
            $(".card-body").html(response);
            mode = 2;
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

function elasticRegister() {
    window.location.href = "/elastic/xdm/ElasticXdmForm";
}

function changeIndex() {
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/elastic/xdm/ElasticXdmIndexChange"
        ,data: { "index" : $("#index").val() }
        ,success: function(response) {
            $("blockquote").html(response);
        }
        ,error : function(jqXHR){
            $("blockquote").html("");
        }
    });
}

const ENGLISH = /[a-zA-Z]/;
const KOREAN = /[가-힣]/;
const regex = /^\/[a-zA-Z]+.*$/;
const ENG_N_KOR = /[a-zA-Z가-힣]/;

function validation() {
    let text = "<div class='fs-10 error' style='color:red;'>text</div>";
    if ($("#index").val() === "") {
        text = text.replace("text", "index 선택해주세요!");
        $("#index").parent().append(text);
        return false;
    }

    if ($("#index").val() === "loveis") {
        if (!KOREAN.test($("#name").val())) {
            text = text.replace("text", "한글 이름을 입력해주세요!");
            $("#name").parent().append(text);
            return false;
        }

        if (!ENGLISH.test($("#engName").val())) {
            text = text.replace("text", "영문 이름을 입력해주세요!");
            $("#engName").parent().append(text);
            return false;
        }
    
        if (!regex.test($("#url").val())) {
            text = text.replace("text", "올바른 주소를 입력해주세요! (/example...)");
            $("#url").parent().append(text);
            return false;
        }
    } else if ($("#index").val() === "peterpet") {
        if (!ENG_N_KOR.test($("#name").val())) {
            text = text.replace("text", "올바른 이름을 입력해주세요!");
            $("#name").parent().append(text);
            return false;
        }

        if ($("#type").val() !== 3) {
            if ($("#brand").val() === "") {
                text = text.replace("text", "brand 선택해주세요!");
                $("#brand").parent().append(text);
                return false;
            }
        }
    }

    return true;
}

function elasticDoc() {
    $(".error").remove();

    if (!validation()) {
        return;
    }

    let formData = new FormData;
    formData.append("index", $("#index").val());
    formData.append("id", $("#id").val());
    formData.append("name", $("#name").val());

    if ($("#index").val() === "loveis") {
        formData.append("engName", $("#engName").val());
        formData.append("url", $("#url").val());
    } else if ($("#index").val() === "peterpet") {
        formData.append("type", $("#type").val());
        formData.append("brand", $("#brand").val());
    }

    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/elastic/xdm/ElasticXdmDocRegister"
        ,data: formData
        ,processData: false
        ,contentType: false
        ,success: function(response) {
            window.location.href = "/elastic/xdm/ElasticXdmList";
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

function elasticIndexReg() {
    $("#elasticModal").show();
}

function elasticlose() {
    $("#elasticModal").hide();
}

const LOWER_ENGLISH = /[a-z]/;

function indexRegister() {
    $(".error").remove();

    if (!LOWER_ENGLISH.test($("#indexText").val())) {
        let text = "<div class='fs-10 error' style='color:red;'>소문자만 입력가능합니다!</div>";
        $("#indexText").parent().append(text);
        return;
    }

    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/elastic/xdm/ElasticXdmIndexRegister"
        ,data: { "index" : $("#indexText").val() }
        ,success: function(response) {
            window.location.href = "/elastic/xdm/ElasticXdmList";
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

function elasticDelete() {
    if (mode == 0) {
        return;
    }

    for (let i = 0; i < $(".listCheckBox").length; i++) {
        if ($(".listCheckBox").eq(i).is(":checked")) {
            let row = $(".btn-reveal-trigger").eq(i);
            if (mode == 1) {
                elasticIndexDelete(row);
            } else if (mode == 2) {
                elasticDocDelete(row);
            }
        }
    }
}

function elasticAllCheck(e) {
    if ($(e).is(":checked")) {
        $(".listCheckBox").prop("checked", true);
    } else {
        $(".listCheckBox").prop("checked", false);
    }
}

function elasticIndexDelete(r) {
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/elastic/xdm/ElasticXdmIndexDelete"
        ,data: { "index" : r.find(".content").eq(2).text() }
        ,success: function(response) {
            window.location.href = "/elastic/xdm/ElasticXdmList";
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

function elasticDocDelete(r) {
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/elastic/xdm/ElasticXdmDocumentDelete"
        ,data: { "index" : $("#shIndex").val(), "id" : r.find(".documentId").text() }
        ,success: function(response) {
            window.location.href = "/elastic/xdm/ElasticXdmList";
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

function typeChange(e) {
    if ($(e).val() == 3) {
        $("#brand").prop("disabled", true);
    } else {
        $("#brand").prop("disabled", false);
    }

    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/elastic/xdm/ElasticXdmTypeBrand"
        ,data: { "type" : $(e).val() }
        ,success: function(response) {
            $("#brand").html(response);
        }
        ,error : function(jqXHR){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}