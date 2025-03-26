// 공통

const code = 1;
const member = 2;
const pet = 3;
const product = 4;

let form = document.querySelector("form[name=formList]");
goList = function (thisPage) {
    document.querySelector("input[name=thisPage]").value = thisPage;
    form.action = goUrlXdmList;
    form.submit();
}

search = function () {
  form.action = goUrlXdmList;
  form.submit();
}

changeRow = function () {
  document.getElementById("rowNumToShow").value = document.getElementById("changeRowNum").value;
  form.action = goUrlXdmList;
  form.submit();
}

shReset = function (flag) {
  if (flag == code) {
    document.getElementById("shUseFlag").value = 1;
    document.getElementById("shOption").value = null;
  } else if (flag == member) {
    document.getElementById("shOption").value = null;
  } else if (flag == pet) {
    document.getElementById("shOption").value = null;
    document.getElementById("shVaccineFlag").value = null;
    document.getElementById("shNeuterFlag").value = null;
    document.getElementById("shMinWeight").value = 0;
    document.getElementById("shMaxWeight").value = 100;
  } else if (flag == product) {
    document.getElementById("shFeedSalaryAge").value = null;
    document.getElementById("shFeedType").value = null;
    document.getElementById("shFeedEtc").value = null;
    document.getElementById("shFeedSize").value = null;
    document.getElementById("shFeedBrand").value = null;
    document.getElementById("shFeedIngredient").value = null;
    document.getElementById("registerFlag").value = 0;
  }
  document.getElementById("shDelFlag").value = 0;
  document.getElementById("shOptionDate").value = "";
  document.getElementById("shValue").value = null;
  form.action = goUrlXdmList;
  form.submit();
}

document.getElementById("registerBtn").onclick = function() {
  document.getElementById("registerOrModifyFlag").value = 1;
  form.action = goUrlXdmForm;
  form.submit();
}