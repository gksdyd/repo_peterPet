/* ===============================================-->
                    등록 관련 JS
   ===============================================--> */

// 등록일 및 수정일 계산 함수
calculateToday = function() {
    let today = new Date();
    let year = today.getFullYear();
    let month = (today.getMonth() + 1).toString().padStart(2, '0');
    let day = today.getDate().toString().padStart(2, '0');
    return year + month + day;
}
  
// 등록 및 수정하는 함수
document.getElementById("btnSubmit").onclick = function() {
    validationInit();

    if (!validation()) {
        return false;
    }

    if (document.getElementById("registerOrModifyFlag").value == 1) {
        registerDate.value = calculateToday();
        form.action = goUrlXdmInst;
    } else {
        modifyDate.value = calculateToday();
        form.action = goUrlPeterUpdt;
    }

    form.method = "post";
    form.submit();
}