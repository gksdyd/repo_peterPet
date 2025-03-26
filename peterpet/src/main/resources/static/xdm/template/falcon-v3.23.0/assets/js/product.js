/* ===============================================-->
                      상품 관련 JS
   ===============================================--> */

// 상품 종류에 따른 필터 및 등록 폼 변경 함수
changeProductType = function (formId, address) {
  let form = document.querySelector("form[name=" + formId + "]");
  form.action = address;
  form.submit();
}

// 상품의 기능을 여러개 설정하는 함수
funcSelect = function (formId, address, value, flag) {
  let form = document.querySelector("form[name=" + formId + "]");
  document.getElementById("addOrRemoveFlag").value = flag;
  document.getElementById("registerFlag").value = "1";
  document.getElementById("prodFunction").value = value;
  form.action = address;
  form.submit();
}