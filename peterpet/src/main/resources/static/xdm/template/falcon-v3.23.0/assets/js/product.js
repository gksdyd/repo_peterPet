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
$("#prodFunction").change(function(){
  funcArray.push($(this).val());
  // alert(funcArray.join(""));
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
      $("#createFunction").append(funcCloseBtn);

      for (let j = 1; j < document.querySelectorAll("#prodFunction option").length; j++) {
        if (document.querySelectorAll("#prodFunction option")[j].value == response.funcSeq) {
          document.querySelectorAll("#prodFunction option")[j].setAttribute("disabled", "true");
          break;
        }
      }
    }
    ,error : function(jqXHR){
      alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
    }
  });
});

// $(document).on('click', '.removeFunc', function(){
//   alert($(this));
// });

