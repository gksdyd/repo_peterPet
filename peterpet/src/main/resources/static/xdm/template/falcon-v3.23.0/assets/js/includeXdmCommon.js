// 공통 사용
const URL_SIGNIN_FORM_XDM = "/xdm/member/LoginXdmForm"
const URL_SIGNOUT_PROC_XDM = "/xdm/member/LogoutXdmProc"
const URL_INDEX_VIEW_XDM = "/xdm/index/IndexXdmView"

$("#btnLogout").on("click", function(){
  alert("fds");
  $.ajax({
    async: true 
    ,cache: false
    ,type: "post"
    ,url: URL_SIGNOUT_PROC_XDM
    ,data: {}
    ,success: function(response) {
      if(response.rt == "success") {
        location.href = URL_SIGNIN_FORM_XDM;
      } else {
        // by pass
      }
    }
    ,error : function(jqXHR){
      alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
    }
  });
});

// 상품

changeProductType = function (formId, address) {
  let form = document.querySelector("form[name=" + formId + "]");
  form.action = address;
  form.submit();
}

funcSelect = function (formId, address, value, flag) {
  let form = document.querySelector("form[name=" + formId + "]");
  document.getElementById("addOrRemoveFlag").value = flag;
  document.getElementById("registerFlag").value = "1";
  document.getElementById("prodFunction").value = value;
  form.action = address;
  form.submit();
}