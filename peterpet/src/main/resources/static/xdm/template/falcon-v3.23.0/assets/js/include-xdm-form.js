
let form = document.querySelector("form[name=product-form]");
funcSelect = function (value, flag) {
  document.getElementById("addOrRemoveFlag").value = flag;
  document.getElementById("registerFlag").value = "1";
  document.getElementById("feedFunction").value = value;
  form.action = "/xdm/product/ProductXdmForm";
  form.submit();
}

changeProductType = function () {	
	form.action = "/xdm/product/ProductXdmForm";
  form.submit();
}