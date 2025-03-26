// list, form 공통 사용

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