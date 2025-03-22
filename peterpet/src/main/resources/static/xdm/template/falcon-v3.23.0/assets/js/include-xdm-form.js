let form = document.querySelector("form[name=product-form]");
selectFeature = function () {
  alert(document.querySelector("#feedFunction").querySelectorAll("option").length);
  // document.querySelector("#feedFunction").querySelectorAll("option").length

  document.getElementById("registerFlag").value = "1";
  form.action = "/xdm/product/ProductXdmForm";
  form.submit();
}

cancel = function() {
}

changeProductType = function () {	
	document.getElementById("feedFilter").style.display = "none";

	if (document.getElementById("prodType").value == 1) {
		document.getElementById("feedFilter").style.display = "block";
	}
}