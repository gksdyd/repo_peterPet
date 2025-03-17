let form = document.querySelector("form[name=formList]");
  goList = function (thisPage, address) {
      document.querySelector("input[name=thisPage]").value = thisPage;
      form.action = address;
      form.submit();
  }
  
  changeProductType = function () {	
	document.getElementById("feedFilter").style.display = "none";

	if (document.getElementById("selectProductType").value == 1) {
		document.getElementById("feedFilter").style.display = "block";
	}
  }