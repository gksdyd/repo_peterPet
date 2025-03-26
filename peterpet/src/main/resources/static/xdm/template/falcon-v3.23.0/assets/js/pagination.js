/* ===============================================-->
                    페이지 관련 JS
   ===============================================--> */

// 페이지 이동 시, 실행되는 함수
goList = function (thisPage) {
    document.querySelector("input[name=thisPage]").value = thisPage;
    form.action = goUrlXdmList;
    form.submit();
}

// 한 페이지에 나타나는 리스트 수 설정 함수
changeRow = function () {
    document.getElementById("rowNumToShow").value = document.getElementById("changeRowNum").value;
    form.action = goUrlXdmList;
    form.submit();
}