/* ===============================================-->
                      search JS
   ===============================================-->*/
// 돋보기 버튼 함수(현재 페이지 로딩)
search = function () {
  form.action = goUrlXdmList;
  form.submit();

}

// 현재 검색 리스트 초기화 함수
shReset = function () {
  location = goUrlXdmList;
}