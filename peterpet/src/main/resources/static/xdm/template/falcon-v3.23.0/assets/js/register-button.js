/* ===============================================-->
                    등록 버튼 관련 JS
   ===============================================--> */

// 등록 버튼 누를 시, 등록 플래그 ON시키는 함수(등록과 수정을 같은 폼 사용하므로 구별하기 위해)
document.getElementById("registerBtn").onclick = function() {
    document.getElementById("registerOrModifyFlag").value = 1;
    if (document.getElementById("prodType") != null) {
        document.getElementById("prodType").value = 1;
    }
    form.action = goUrlXdmForm;
    form.submit();
}