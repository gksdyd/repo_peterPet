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
var funcArray = [];
var funcIdArray = [];

$("#prodFunction").change(function(){
  $.ajax({
    async: true 
    ,cache: false
    ,type: "post"
    ,url: URL_PRODUCT_FUNCTION_XDM
    ,data: { "prodFunction" : $(this).val() }
    ,success: function(response) {
      let funcBadge = $("<span></span>").addClass("badge").addClass("rounded-pill").addClass("badge-subtle-primary");
      let funcCloseBtn = $("<button></button>").addClass("btn-close").addClass("removeFunc").css('font-size', 'xx-small').prop("type", "button");
      $("#createFunction").append(funcBadge.text(response.funcName));
      $("#createFunction").append(funcCloseBtn.attr('value', response.funcSeq));

      for (let j = 1; j < document.querySelectorAll("#prodFunction option").length; j++) {
        if (document.querySelectorAll("#prodFunction option")[j].value == response.funcSeq) {
          document.querySelectorAll("#prodFunction option")[j].setAttribute("disabled", "true");
          funcArray.push(response.funcSeq);
          funcIdArray.push(FUNCTION_ID_ARRAY[j - 1]);
          break;
        }
      }
    }
    ,error : function(jqXHR){
      alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
    }
  });
});

$(document).on('click', '.removeFunc', function(){
  for (let i = 0; i < funcArray.length; i++) {
    if (funcArray[i] == $(this).val()) {
      document.querySelectorAll(".removeFunc")[i].remove();
      document.querySelectorAll(".badge-subtle-primary")[i].remove();
      funcArray.splice(i, 1);
      funcIdArray.splice(i, 1);
      break;
    }
  }

  for (let j = 1; j < document.querySelectorAll("#prodFunction option").length; j++) {
    if (document.querySelectorAll("#prodFunction option")[j].value == $(this).val()) {
      document.querySelectorAll("#prodFunction option")[j].removeAttribute("disabled");
      break;
    }
  }
});

submitFunc = function() {
  for (let i = 0; i < funcArray.length; i++) {
    $("#" + funcIdArray[i]).val(funcArray[i]);
  }
}

$("#btnSearch").on('click', function() {
  submitFunc();
  search();
});