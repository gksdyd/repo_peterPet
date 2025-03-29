/* ===============================================-->
                      상품 관련 JS
   ===============================================--> */

// 상품 종류에 따른 필터 및 등록 폼 변경 함수
changeProductType = function (formId, address) {
  let form = document.querySelector("form[name=" + formId + "]");
  form.action = address;
  form.submit();
}

funcRemove = function(value) {
  for (let i = 1; i < document.querySelectorAll("#prodFunction option").length; i++) {
    if (document.querySelectorAll("#prodFunction option")[i].value == value) {
      document.querySelectorAll("#prodFunction option")[i].removeAttribute("disabled");
      $("#prodFunction").val(value);
      break;
    }
  }
  funcSelect(REMOVE_FUNCTION);
}

// 상품의 기능을 여러개 설정하는 함수
funcSelect = function (flag) {
  $.ajax({
    async: true 
    ,cache: false
    ,type: "post"
    ,url: URL_PRODUCT_FUNCTION_XDM
    ,data: { "prodFunction" : $("#prodFunction").val(), "addOrRemoveFlag" : flag }
    ,success: function(response) {
      $("#createFunction").empty();
      for (let i = 0; i < response.funcArr.length; i++) {
        let funcBadge = $("<span></span>").addClass("badge").addClass("rounded-pill").addClass("badge-subtle-primary");
        let funcCloseBtn = $("<button></button>").addClass("btn-close").css('font-size', 'xx-small').prop("type", "button");
        $("#createFunction").append(funcBadge.text(response.funcNameArr[i]));
        $("#createFunction").append(funcCloseBtn.attr("onclick", "funcRemove(" + response.funcArr[i] + ");"));

        for (let j = 1; j < document.querySelectorAll("#prodFunction option").length; j++) {
          if (document.querySelectorAll("#prodFunction option")[j].value == response.funcArr[i]) {
            document.querySelectorAll("#prodFunction option")[j].setAttribute("disabled", "true");
            break;
          }
        }
      }
    }
    ,error : function(jqXHR){
      alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
    }
  });
}

// Init
funcSelect(MAINTAIN_FUNCTION);

