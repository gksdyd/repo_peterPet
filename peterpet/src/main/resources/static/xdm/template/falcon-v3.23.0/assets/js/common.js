/* ===============================================-->
                      로그아웃 JS
   ===============================================--> */

// 로그아웃 클릭 시 실행되는 Ajax
$("#btnLogout").on("click", function(){
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