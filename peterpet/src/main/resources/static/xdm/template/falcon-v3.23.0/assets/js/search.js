search = function () {
    form.action = goUrlXdmList;
    form.submit();
}

shReset = function (flag) {
    if (flag == CODE) {
      document.getElementById("shUseFlag").value = 1;
      document.getElementById("shOption").value = null;
    } else if (flag == MEMBER) {
      document.getElementById("shOption").value = null;
    } else if (flag == PET) {
      document.getElementById("shOption").value = null;
      document.getElementById("shVaccineFlag").value = null;
      document.getElementById("shNeuterFlag").value = null;
      document.getElementById("shMinWeight").value = 0;
      document.getElementById("shMaxWeight").value = 100;
    } else if (flag == PRODUCT) {
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