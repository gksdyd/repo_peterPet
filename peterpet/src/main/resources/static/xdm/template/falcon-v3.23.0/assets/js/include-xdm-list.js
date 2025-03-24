// 공통

let form = document.querySelector("form[name=formList]");
goList = function (thisPage, address) {
    document.querySelector("input[name=thisPage]").value = thisPage;
    form.action = address;
    form.submit();
}

search = function (address) {
  form.action = address;
  form.submit();
}

changeRow = function (address) {
  document.getElementById("rowNumToShow").value = document.getElementById("changeRowNum").value;
  form.action = address;
  form.submit();
}

const code = 1;
const member = 2;
const pet = 3;
shReset = function (address, flag) {
  if (flag == code) {
    document.getElementById("shUseFlag").value = 1;
  } else if (flag == member) {

  } else if (flag == pet) {
    document.getElementById("shVaccineFlag").value = null;
    document.getElementById("shNeuterFlag").value = null;
    document.getElementById("shMinWeight").value = 0;
    document.getElementById("shMaxWeight").value = 100;
  }
  document.getElementById("shDelFlag").value = 0;
  document.getElementById("shOptionDate").value = "";
  document.getElementById("shOption").value = null;
  document.getElementById("shValue").value = null;
  form.action = address;
  form.submit();
}