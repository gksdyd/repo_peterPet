
let form = document.querySelector("form[name=product-form]");
funcSelect = function () {
  document.getElementById("addOrRemoveFlag").value = "1";
  document.getElementById("registerFlag").value = "1";
  form.action = "/xdm/product/ProductXdmForm";
  form.submit();
}

funcRemove = function(value) {
  document.getElementById("addOrRemoveFlag").value = "-1";
  document.getElementById("registerFlag").value = "1";
  document.getElementById("feedFunction").value = value;
  form.action = "/xdm/product/ProductXdmForm";
  form.submit();
}

changeProductType = function () {	
	document.getElementById("feedFilter").style.display = "none";

	if (document.getElementById("prodType").value == 1) {
		document.getElementById("feedFilter").style.display = "block";
	}
}