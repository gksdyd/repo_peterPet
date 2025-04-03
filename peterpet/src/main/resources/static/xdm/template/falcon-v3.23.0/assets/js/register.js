/* ===============================================-->
                    등록 관련 JS
   ===============================================--> */
  
// 등록 및 수정하는 함수
document.getElementById("btnSubmit").onclick = function() {
    validationInit();
    if (!validation()) {
        return false;
    }

    if (document.getElementById("registerOrModifyFlag").value == 1) {
        form.action = goUrlXdmInst;
    } else {
        form.action = goUrlXdmUpdt;
    }

    form.method = "post";
    form.submit();
}